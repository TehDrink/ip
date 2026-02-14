package spark.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import spark.exceptions.SparkException;
import spark.tasks.DeadlineTask;
import spark.tasks.EventTask;
import spark.tasks.Task;
import spark.tasks.TaskList;

/**
 * Handles the interpretation and execution of user commands.
 * The Parser takes raw input strings and converts them into actions
 * performed on the TaskList object.
 */
public class Parser {
    private String input;

    /**
     * Constructs the Parser class. (Constructor)
     *
     * @param input The raw user input string to be parsed.
     */
    public Parser(String input) {
        this.input = input;
    }

    // Level-1 & Level-2 & Level-3 & Level-4 & Level-9
    // Echo
    // Add, List
    // Mark as Done
    // ToDos, Events, Deadlines
    // Find

    /**
     * Parses the user input and executes the corresponding command on the TaskList.
     * Handles creating tasks (Todo, Deadline, Event), marking/unmarking tasks,
     * deleting tasks, and listing all tasks.
     *
     * @param taskList The TaskList object to perform actions on.
     * @return A response string indicating the result of the command to be supplied to Ui.
     * @throws SparkException If the command is invalid or cannot be executed.
     * @throws IOException    If there is an error reading or writing data.
     */
    public String parse(TaskList taskList) throws SparkException, IOException {
        if (input.equals("bye")) {
            return null;
        } else if (input.equals("list")) {
            return handleList(taskList);
        } else if (Pattern.matches("^mark [0-9]+$", input)) { // Regex to check specifically for input
            return handleMark(taskList);
        } else if (Pattern.matches("^unmark [0-9]+$", input)) { // Regex to check specifically for input
            return handleUnmark(taskList);
        } else if (Pattern.matches("^todo .+$", input)) {
            return handleTodo(taskList);
        } else if (Pattern.matches("^deadline .+ /by .+$", input)) { // Checks for specific
            return handleDeadline(taskList);
        } else if (Pattern.matches("^event .+ /from .+ /to .+$", input)) { // Checks for specific
            return handleEvent(taskList);
        } else if (Pattern.matches("^delete [0-9]+$", input)) { // Regex to check specifically for input
            return handleDelete(taskList);
        } else if (Pattern.matches("^find .+$", input)) { // Regex to check specifically for input
            return handleFind(taskList);
        } else if (Pattern.matches("^edit [0-9]+ .+$", input)) { // Regex to check specifically for input
            // Edit index, choose what to change.
            return handleEdit(taskList);
        } else { // Handle Invalid
            return handleInvalidCommand();
        }
    }

    /**
     * Handles the "list" command to display all tasks in the TaskList.
     *
     * @param taskList The TaskList object containing the tasks.
     * @return A formatted string listing all tasks.
     */
    private String handleList(TaskList taskList) {
        StringBuilder text = new StringBuilder();

        for (int i = 0; i < taskList.getSize(); i++) {
            text.append(i + 1).append(". ").append(taskList.getTask(i).getTaskInfo());
            if (i + 1 != taskList.getSize()) {
                text.append("\n");
            }
        }
        return text.toString();
    }

    /**
     * Handles the "mark" command to mark a task as done.
     *
     * @param taskList The TaskList object containing the tasks.
     * @return A formatted string indicating the task has been marked as done.
     * @throws SparkException If the specified task does not exist.
     */
    private String handleMark(TaskList taskList) throws SparkException {
        StringBuilder text = new StringBuilder();

        input = input.replaceAll("[^0-9]", "");
        int target = Integer.parseInt(input) - 1;
        if (target >= taskList.getSize() || target < 0) {
            throw new SparkException("Grrr, item does not exist! Try another Id Bark!");
        } else {
            taskList.getTask(target).markAsDone();
            text.append("Marked as done! Awoo!\n");
            text.append(taskList.getTask(target).getTaskInfo());
        }

        return text.toString();
    }

    /**
     * Handles the "unmark" command to mark a task as not done.
     *
     * @param taskList The TaskList object containing the tasks.
     * @return A formatted string indicating the task has been marked as not done.
     * @throws SparkException If the specified task does not exist.
     */
    private String handleUnmark(TaskList taskList) throws SparkException {
        StringBuilder text = new StringBuilder();

        // Extract number - using regex
        input = input.replaceAll("[^0-9]", "");
        int target = Integer.parseInt(input) - 1;
        if (target >= taskList.getSize() || target < 0) {
            throw new SparkException("Grrr, item does not exist! Try another Id Bark!");
        } else {
            taskList.getTask(target).markAsNew();
            text.append("Marked as new! Awoo!\n");
            text.append(taskList.getTask(target).getTaskInfo());
        }

        return text.toString();
    }

    /**
     * Handles the "deadline" command to add a new DeadlineTask to the TaskList.
     *
     * @param taskList The TaskList object to add the task to.
     * @return A formatted string indicating the task has been added.
     * @throws IOException If there is an error adding the task.
     */
    private String handleDeadline(TaskList taskList) throws IOException {
        StringBuilder text = new StringBuilder();

        // Deadline Task
        // Extract Task
        String taskDescription = input.substring(input.indexOf("deadline ") + 9, input.indexOf(" /by"));
        assert !taskDescription.isEmpty() : "Task description cannot be empty after regex match";

        // Extract Date
        String deadline = input.substring(input.indexOf("/by ") + 4);
        // Add to list
        try {
            // Create the task (This will now fail if date is not yyyy-mm-dd)
            DeadlineTask item = new DeadlineTask(taskDescription, deadline);
            taskList.addTask(item);

            text.append("Bark! I've added the task!").append("\n");
            text.append(item.getTaskInfo());
            text.append("\nYou now have ").append(taskList.getSize()).append(" tasks in list!");

        } catch (java.time.format.DateTimeParseException e) {
            text.append("Bark! Invalid format. Use: yyyy-MM-dd HHmm\n");
            text.append("Example: deadline return book /by 2019-12-02 1800");
        }
        return text.toString();
    }

    /**
     * Handles the "todo" command to add a new Task to the TaskList.
     *
     * @param taskList The TaskList object to add the task to.
     * @return A formatted string indicating the task has been added.
     * @throws IOException If there is an error adding the task.
     */
    private String handleTodo(TaskList taskList) throws IOException {
        StringBuilder text = new StringBuilder();

        // ToDo Task
        // Extract Task
        String taskDescription = input.substring(input.indexOf("todo ") + 5).trim();
        assert !taskDescription.isEmpty() : "Task description cannot be empty after regex match";
        // Add to list
        Task item = new Task(taskDescription);
        taskList.addTask(item);

        // Response
        text.append("Bark! I've added the task!").append("\n");
        text.append(item.getTaskInfo());
        text.append("\nYou now have ").append(taskList.getSize()).append(" tasks in list!");

        return text.toString();
    }

    /**
     * Handles the "event" command to add a new EventTask to the TaskList.
     *
     * @param taskList The TaskList object to add the task to.
     * @return A formatted string indicating the task has been added.
     * @throws IOException If there is an error adding the task.
     */
    private String handleEvent(TaskList taskList) throws IOException {
        StringBuilder text = new StringBuilder();

        // Event Task
        // Extract Task
        String taskDescription = input.substring(input.indexOf("event ") + 6, input.indexOf(" /from"));
        assert !taskDescription.isEmpty() : "Task description cannot be empty after regex match";

        // Extract StartDate
        String startDate = input.substring(input.indexOf("/from ") + 6, input.indexOf(" /to"));
        // Extract StartTime
        // String startTime = input.substring(input.indexOf(" ") + 1, input.indexOf(" /to"));
        // Extract EndDate
        String endDate = input.substring(input.indexOf("/to ") + 4);

        try {
            EventTask item = new EventTask(taskDescription, startDate, endDate);
            taskList.addTask(item);

            text.append("Bark! I've added the Event!\n");
            text.append(item.getTaskInfo()).append("\n");
            text.append("You now have ").append(taskList.getSize()).append(" tasks in list!");

        } catch (java.time.format.DateTimeParseException e) {
            text.append("Bark! Invalid format. Use: yyyy-MM-dd HHmm\n");
            text.append("Example: event meeting /from 2019-12-02 1400 /to 2019-12-02 1600");
        }

        return text.toString();
    }

    /**
     * Handles the "delete" command to remove a task from the TaskList.
     *
     * @param taskList The TaskList object containing the tasks.
     * @return A formatted string indicating the task has been removed.
     * @throws SparkException If the specified task does not exist.
     * @throws IOException    If there is an error removing the task.
     */
    private String handleDelete(TaskList taskList) throws SparkException, IOException {
        StringBuilder text = new StringBuilder();

        // Extract number - using regex
        input = input.replaceAll("[^0-9]", "");
        int target = Integer.parseInt(input) - 1;
        if (target >= taskList.getSize() || target < 0) {
            throw new SparkException("Grrr, item does not exist! Try another Id Bark!");
        } else {
            text.append("Task removed! Removed the following Task Woof!\n");
            text.append(taskList.getTask(target).getTaskInfo()).append("\n");
            taskList.removeTask(target);
            text.append("You now have ").append(taskList.getSize()).append(" tasks in list!");
        }

        return text.toString();
    }

    /**
     * Handles the "find" command to search for tasks containing a specific keyword.
     *
     * @param taskList The TaskList object containing the tasks.
     * @return A formatted string listing the matching tasks.
     */
    private String handleFind(TaskList taskList) {
        StringBuilder text = new StringBuilder();

        String searchString = input.substring(input.indexOf("find ") + 5);
        ArrayList<Task> results = taskList.findTasks(searchString);
        if (results.isEmpty()) {
            text.append("Bark! No matching tasks found!");
        } else {
            text.append("Bark! Here are the matching tasks in your list:\n");
            for (int i = 0; i < results.size(); i++) {
                text.append(i + 1).append(". ").append(results.get(i).getTaskInfo());
                if (i + 1 != results.size()) {
                    text.append("\n");
                }
            }
        }

        return text.toString();
    }

    /**
     * Handles invalid commands by throwing appropriate SparkException messages.
     *
     * @return This method does not return a value, it always throws an exception.
     * @throws SparkException Indicating the nature of the invalid command.
     */
    private String handleInvalidCommand() throws SparkException {
        if (input.contains("todo")) {
            throw new SparkException("Bark? todo (fill)?");
        } else if (input.contains("deadline")) {
            throw new SparkException("Bark? deadline (fill) /by (fill)?");
        } else if (input.contains("event")) {
            throw new SparkException("Bark? event (fill) /from (fill) /to (fill)?");
        } else if (input.contains("edit")) {
            throw new SparkException("Bark?"
                    + "\nedit (fill index) /desc (fill)"
                    + "\nedit (fill index) /by (fill)"
                    + "\nedit (fill index) /from (fill)"
                    + "\nedit (fill index) /to (fill)?");
        }
        throw new SparkException("Bark? I didn't quite get that! Fetch me a proper command!");
    }

    private String handleEdit(TaskList taskList) throws SparkException, IOException {
        String[] split = input.split(" ", 3);
        if (split.length < 3) {
            throw new SparkException("Bark! Invalid format. Use: edit <id> /desc <text>");
        }

        int target = Integer.parseInt(split[1]) - 1;
        if (target >= taskList.getSize() || target < 0) {
            throw new SparkException("Grrr, item does not exist! Try another Id Bark!");
        }

        Task task = taskList.getTask(target);
        String editCommand = split[2].trim();

        try {
            if (editCommand.startsWith("/desc ")) {
                String newDesc = editCommand.substring(6).trim();
                assert !newDesc.isEmpty() : "Description cannot be empty";
                task.setDescription(newDesc);

            } else if (editCommand.startsWith("/by ")) {
                if (!(task instanceof DeadlineTask deadlineTask)) {
                    throw new SparkException("Bark!\nThis is not a Deadline task! Cannot edit /by");
                }
                String newBy = editCommand.substring(4).trim();
                deadlineTask.setEndDateIso(newBy);

            } else if (editCommand.startsWith("/from ")) {
                if (!(task instanceof EventTask eventTask)) {
                    throw new SparkException("Bark!\nThis is not an Event task! Cannot edit /from");
                }
                String newFrom = editCommand.substring(6).trim();
                eventTask.setStartDateIso(newFrom);

            } else if (editCommand.startsWith("/to ")) {
                if (!(task instanceof EventTask eventTask)) {
                    throw new SparkException("Bark!\nThis is not an Event task! Cannot edit /to");
                }
                String newTo = editCommand.substring(4).trim();
                eventTask.setEndDateIso(newTo);

            } else {
                throw new SparkException("Bark?"
                        + "\nedit (fill index) /desc (fill)"
                        + "\nedit (fill index) /by (fill)"
                        + "\nedit (fill index) /from (fill)"
                        + "\nedit (fill index) /to (fill)?");
            }
        } catch (java.time.format.DateTimeParseException e) {
            throw new SparkException("Bark!\nInvalid date format. Use: yyyy-MM-dd HHmm");
        }

        taskList.saveTasks(); // Save changes to hard drive
        return "Bark! Task updated successfully!\n" + task.getTaskInfo();
    }
}


