package spark.commands;

import spark.exceptions.SparkException;
import spark.tasks.TaskList;
import spark.tasks.DeadlineTask;
import spark.tasks.EventTask;
import spark.tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Parser {
    String input;

    public Parser(String input) {
        this.input = input;
    }

    // Level-1 & Level-2 & Level-3 & Level-4 & Level-9
    // Echo
    // Add, List
    // Mark as Done
    // ToDos, Events, Deadlines
    // Find
    public String parse(TaskList taskList) throws SparkException, IOException {
        StringBuilder text = new StringBuilder();

        if (input.equals("bye")) {
            return null;
        } else if (input.equals("list")) {
            for (int i = 0; i < taskList.size(); i++) {
                text.append(i + 1).append(". ").append(taskList.getTask(i).getTaskInfo());
                if (i + 1 != taskList.size()) {
                    text.append("\n");
                }
            }

        } else if (Pattern.matches("^mark [0-9]+$", input)) { // Regex to check specifically for input
            // Extract number - using regex
            input = input.replaceAll("[^0-9]", "");
            int target = Integer.parseInt(input) - 1;
            if (target >= taskList.size() || target < 0) {
                throw new SparkException("Grrr, item does not exist! Try another Id Bark!");
            } else {
                taskList.getTask(target).markAsDone();
                text.append("Marked as done! Awoo!\n");
                text.append(taskList.getTask(target).getTaskInfo());
            }

        } else if (Pattern.matches("^unmark [0-9]+$", input)) { // Regex to check specifically for input
            // Extract number - using regex
            input = input.replaceAll("[^0-9]", "");
            int target = Integer.parseInt(input) - 1;
            if (target >= taskList.size() || target < 0) {
                throw new SparkException("Grrr, item does not exist! Try another Id Bark!");
            } else {
                taskList.getTask(target).markAsNew();
                text.append("Marked as new! Awoo!\n");
                text.append(taskList.getTask(target).getTaskInfo());
            }

        } else if (Pattern.matches("^deadline .+ /by .+$", input)) { // Checks for specific
            // Deadline Task
            // Extract Task
            String taskDescription = input.substring(input.indexOf("deadline ") + 9, input.indexOf(" /by"));
            // Extract Date
            String deadline = input.substring(input.indexOf("/by ") + 4);
            // Add to list
            try {
                // Create the task (This will now fail if date is not yyyy-mm-dd)
                DeadlineTask item = new DeadlineTask(taskDescription, deadline);
                taskList.addTask(item);

                text.append("Bark! I've added the task!").append("\n");
                text.append(item.getTaskInfo());
                text.append("\nYou now have ").append(taskList.size()).append(" tasks in list!");

            } catch (java.time.format.DateTimeParseException e) {
                text.append("Bark! Invalid format. Use: yyyy-MM-dd HHmm\n");
                text.append("Example: deadline return book /by 2019-12-02 1800");
            }

        } else if (Pattern.matches("^todo .+$", input)) {
            // ToDo Task
            // Extract Task
            String taskDescription = input.substring(input.indexOf("todo ") + 5);

            // Add to list
            Task item = new Task(taskDescription);
            taskList.addTask(item);

            // Response
            text.append("Bark! I've added the task!").append("\n");
            text.append(item.getTaskInfo());
            text.append("\nYou now have ").append(taskList.size()).append(" tasks in list!");

        } else if (Pattern.matches("^event .+ /from .+ /to .+$", input)) { // Checks for specific

            // Event Task
            // Extract Task
            String taskDescription = input.substring(input.indexOf("event ") + 6, input.indexOf(" /from"));
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
                text.append("You now have ").append(taskList.size()).append(" tasks in list!");

            } catch (java.time.format.DateTimeParseException e) {
                text.append("Bark! Invalid format. Use: yyyy-MM-dd HHmm\n");
                text.append("Example: event meeting /from 2019-12-02 1400 /to 2019-12-02 1600");
            }

        } else if (Pattern.matches("^delete [0-9]+$", input)) { // Regex to check specifically for input
            // Extract number - using regex
            input = input.replaceAll("[^0-9]", "");
            int target = Integer.parseInt(input) - 1;


            if (target >= taskList.size() || target < 0) {
                throw new SparkException("Grrr, item does not exist! Try another Id Bark!");
            } else {
                text.append("Task removed! Removed the following Task Woof!\n");
                text.append(taskList.getTask(target).getTaskInfo()).append("\n");
                taskList.removeTask(target);
                text.append("You now have ").append(taskList.size()).append(" tasks in list!");
            }

        } else if (Pattern.matches("^find .+$", input)) { // Regex to check specifically for input
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
        } else { // Handle Invalid
            if (input.contains("todo")) {
                throw new SparkException("Bark? todo (fill)?");
            } else if (input.contains("deadline")) {
                throw new SparkException("Bark? deadline (fill) /by (fill)?");
            } else if (input.contains("event")) {
                throw new SparkException("Bark? event (fill) /from (fill) /to (fill)?");
            }
            throw new SparkException("Bark? I didn't quite get that! Fetch me a proper command!");
        }

        // Write to data file
        return text.toString();
    }

}
