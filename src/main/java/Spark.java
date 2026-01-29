import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;  // Import the Scanner class
import java.util.regex.Pattern;
import java.io.File; // Necessary for locating the file
import java.io.FileNotFoundException; // Necessary for error handling

public class Spark {
    // Level-0
    // Rename, Greet, Exit

    public static void greet() {
        String logo = " ____                   _\n" +
                "/ ___| _ __   __ _ _ __| | __\n" +
                "\\___ \\| '_ \\ / _` | '__| |/ /\n" +
                " ___) | |_) | (_| | |  |   <\n" +
                "|____/| .__/ \\__,_|_|  |_|\\_\\\n" +
                "      |_|";
        System.out.println(logo);
        System.out.println("----------------");
        System.out.println("Bark! Hello I am Spark! I am dog!");
        System.out.println("What can I fetch for you today, Bark?");
        System.out.println("----------------");

    }

    // Level-1 & Level-2 & Level-3 & Level-4
    // Echo
    // Add, List
    // Mark as Done
    // ToDos, Events, Deadlines
    public static boolean echo(Scanner sc, ArrayList<Task> theList) throws SparkException, IOException {
        // Guard
        if (!sc.hasNextLine()) {
            return false;
        }

        String text = sc.nextLine();

        if (text.equals("bye")) {
            return false;
        } else if (text.equals("list")) {
            System.out.println("----------------");
            for (int i = 0; i < theList.size(); i++) {
                System.out.println(i+1 + ". " + theList.get(i).getTaskInfo());
            }
            System.out.println("----------------");

        } else if (Pattern.matches("^mark [0-9]+$", text)) { // Regex to check specifically for input
            // Extract number - using regex
            text = text.replaceAll("[^0-9]", "");
            int target = Integer.parseInt(text) - 1;
            if (target >= theList.size() || target < 0) {
                throw new SparkException("Grrr, item does not exist! Try another Id Bark!");
            } else {
                theList.get(target).markAsDone();
                System.out.println("----------------");
                System.out.println("Marked as done! Awoo!");
                System.out.println(theList.get(target).getTaskInfo());
                System.out.println("----------------");
            }

        } else if (Pattern.matches("^unmark [0-9]+$", text)) { // Regex to check specifically for input
            // Extract number - using regex
            text = text.replaceAll("[^0-9]", "");
            int target = Integer.parseInt(text) - 1;
            System.out.println("----------------");
            if (target >= theList.size() || target < 0) {
                throw new SparkException("Grrr, item does not exist! Try another Id Bark!");
            } else {
                theList.get(target).markAsNew();
                System.out.println("Marked as new! Awoo!");
                System.out.println(theList.get(target).getTaskInfo());

            }
            System.out.println("----------------");


        } else if (Pattern.matches("^deadline .+ /by .+$", text)) { // Checks for specific
            // Deadline Task
            // Extract Task
            String taskDescription = text.substring(text.indexOf("deadline ") + 9, text.indexOf(" /by"));
            // Extract Date
            String deadline = text.substring(text.indexOf("/by ") + 4);
            // Add to list
            DeadlineTask item = new DeadlineTask(taskDescription, deadline);
            theList.add(item);
            // Response
            System.out.println("----------------");
            System.out.println("Bark! I've added the task!");
            System.out.println(item.getTaskInfo());
            System.out.println("You now have " + theList.size() + " tasks in list!");
            System.out.println("----------------");

        } else if (Pattern.matches("^todo .+$", text)) {
            // ToDo Task
            // Extract Task
            String taskDescription = text.substring(text.indexOf("todo ") + 5);

            // Add to list
            Task item = new Task(taskDescription);
            theList.add(item);

            // Response
            System.out.println("----------------");
            System.out.println("Bark! I've added the task!");
            System.out.println(item.getTaskInfo());
            System.out.println("You now have " + theList.size() + " tasks in list!");
            System.out.println("----------------");

        } else if (Pattern.matches("^event .+ /from .+ /to .+$", text)) { // Checks for specific

            // Event Task
            // Extract Task
            String taskDescription = text.substring(text.indexOf("event ") + 6, text.indexOf(" /from"));
            // Extract StartDate
            String startDate = text.substring(text.indexOf("/from ") + 6, text.indexOf(" /to"));
            // Extract StartTime
            // String startTime = text.substring(text.indexOf(" ") + 1, text.indexOf(" /to"));
            // Extract EndDate
            String endDate = text.substring(text.indexOf("/to ") + 4);

            // Add to list
            EventTask item = new EventTask(taskDescription, startDate, endDate);
            theList.add(item);

            // Response
            System.out.println("----------------");
            System.out.println("Bark! I've added the task!");
            System.out.println(item.getTaskInfo());
            System.out.println("You now have " + theList.size() + " tasks in list!");
            System.out.println("----------------");

        } else if (Pattern.matches("^delete [0-9]+$", text)) { // Regex to check specifically for input
            // Extract number - using regex
            text = text.replaceAll("[^0-9]", "");
            int target = Integer.parseInt(text) - 1;
            System.out.println("----------------");
            if (target >= theList.size() || target < 0) {
                throw new SparkException("Grrr, item does not exist! Try another Id Bark!");
            } else {
                System.out.println("Task removed! Removed the following Task Woof!");
                System.out.println(theList.get(target).getTaskInfo());
                theList.remove(target);
                System.out.println("You now have " + theList.size() + " tasks in list!");

            }
            System.out.println("----------------");

        }

        else { // Handle Invalid
            if (text.contains("todo")) {
                throw new SparkException("Bark? todo (fill)?");
            } else if (text.contains("deadline")) {
                throw new SparkException("Bark? deadline (fill) /by (fill)?");
            } else if (text.contains("event")) {
                throw new SparkException("Bark? event (fill) /from (fill) /to (fill)?");
            }
            throw new SparkException("Bark? I didn't quite get that! Fetch me a proper command!");
        }

        // Write to data file
        saveTasks(theList);

        return true;
    }

    // Level 7
    // Save

    private static final String DATA_PATH = "./data/spark.txt";

    public static void saveTasks(ArrayList<Task> theList) throws IOException {
        try{
            File folder = new File("./data");

            if (!folder.exists()) {
                folder.mkdirs();
            }

            try (FileWriter fw = new FileWriter("./data/spark.txt")) {

                for (Task task : theList) {
                    int status = task.getStatusIcon().equals("X") ? 1 : 0;

                    String line = task.getType() + " | " + status + " | " + task.description;

                    if (task instanceof EventTask) {
                        line += " | " + ((EventTask) task).getStartDate() + " | " + ((EventTask) task).getDeadline();
                    } else if (task instanceof DeadlineTask) {
                        line += " | " + ((DeadlineTask) task).getDeadline();
                    }

                    fw.write(line + System.lineSeparator());
                }
            }

        } catch (IOException e) {
            System.out.println("Bark! I couldn't save my memory: " + e.getMessage());
        }
    }

    public static void loadTasks(ArrayList<Task> theList) throws FileNotFoundException {
        File f = new File(DATA_PATH);
        if (!f.exists()) return;

        Scanner sc = new Scanner(f);

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] parts = line.split(" \\| ");

            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];

            Task task;

            switch (type) {
                case "T":
                    task = new Task(description);
                    break;
                case "D":
                    String by = parts[3];
                    task = new DeadlineTask(description, by);
                    break;
                case "E":
                    String from = parts[3];
                    String to = parts[4];
                    task = new EventTask(description, from, to);
                    break;
                default:
                    continue; // Skip unknown formats
            }

            if (isDone) {
                task.markAsDone();
            }

            theList.add(task);
        }

        sc.close();
    }

    public static void main(String[] args) throws SparkException, FileNotFoundException {

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> theList = new ArrayList<>();

        // Load existing data
        loadTasks(theList);
        // Greet the user
        greet();
        // Initiate Echo
        boolean isRunning = true;
        while (isRunning) {
            try {
                isRunning = echo(sc, theList);
            } catch (SparkException | IOException e) {
                System.out.println("Error Sniffed! " + e.getMessage());
            }
        }
        // Say Goodbye
        System.out.println("----------------");
        System.out.println("See you soon! Bark!\n");

    }
}