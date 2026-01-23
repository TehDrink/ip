import java.util.ArrayList;
import java.util.Scanner;  // Import the Scanner class
import java.util.regex.Pattern;

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
    public static boolean echo(Scanner sc, ArrayList<Task> theList) {
        // Guard
        if (!sc.hasNextLine()) {
            return false;
        }

        String text = sc.nextLine();

        if (text.equals("bye")) {
            return false;
        } else if (text.equals("list")) {
            System.out.println("----------------");
            for (Task task : theList) {
                System.out.println(task.getTaskInfo());
            }
            System.out.println("----------------");

        } else if (Pattern.matches("^mark [0-9]+$", text)) { // Regex to check specifically for input
            // Extract number - using regex
            text = text.replaceAll("[^0-9]", "");
            int target = Integer.parseInt(text) - 1;
            if (target >= theList.size()) {
                System.out.println("Grrr, item does not exist! Try another Id Bark!");
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
            if (target >= theList.size()) {
                System.out.println("Grrr, item does not exist! Try another Id Bark!");

            } else {
                theList.get(target).markAsNew();
                System.out.println("Marked as new! Awoo!");
                System.out.println(theList.get(target).getTaskInfo());

            }
            System.out.println("----------------");


        } else if (Pattern.matches("^deadline .+ /by .+$", text)) { // Checks for specific
            // Deadline Task
            // Extract Task
            String taskDescription = text.substring(text.indexOf("deadline ") + 9, text.indexOf("/by"));
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

        } else { // Add item
            System.out.println("----------------");
            System.out.println("Added: " + text);
            theList.add(new Task(text));
            System.out.println("----------------");
        }

        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> theList = new ArrayList<>();
        // Greet the user
        greet();
        // Initiate Echo
        while (echo(sc, theList)) {
            // While echo is true
        }
        // Say Goodbye
        System.out.println("----------------");
        System.out.println("See you soon! Bark!\n");
    }
}