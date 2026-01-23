import java.util.ArrayList;
import java.util.Scanner;  // Import the Scanner class
import java.util.regex.Pattern;

public class Spark {
    // Level-0
    // Rename, Greet, Exit
    public static void greet() {
        String logo = " ____                   _    \n" +
                "/ ___| _ __   __ _ _ __| | __\n" +
                "\\___ \\| '_ \\ / _` | '__| |/ /\n" +
                " ___) | |_) | (_| | |  |   <\n" +
                "|____/| .__/ \\__,_|_|  |_|\\_\\ \n" +
                "      |_|";
        System.out.println(logo);
        System.out.println("----------------");
        System.out.println("Bark! Hello I am Spark! I am dog!");
        System.out.println("What can I fetch for you today, Bark?");
    }

    // Level-1 & Level-2 & Level-3
    // Echo
    // Add, List
    // Mark as Done
    public static boolean echo(ArrayList<Task> theList) {
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();

        if (text.equals("bye")) {
            return false;

        } else if (text.equals("list")) {
            System.out.println("----------------");
            for (int i = 0; i < theList.size(); i++) {
                System.out.println(theList.get(i).getTaskId() + ".[" + theList.get(i).getStatusIcon() + "] " + theList.get(i).getTask());
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
                System.out.println("Marked as done! Awoo!");
                System.out.println(theList.get(target).getTaskId() + ".[" + theList.get(target).getStatusIcon() + "] " + theList.get(target).getTask());
            }

        } else if (Pattern.matches("^unmark [0-9]+$", text)) { // Regex to check specifically for input
            // Extract number - using regex
            text = text.replaceAll("[^0-9]", "");
            int target = Integer.parseInt(text) - 1;
            if (target >= theList.size()) {
                System.out.println("Grrr, item does not exist! Try another Id Bark!");
            } else {
                theList.get(target).markAsNew();
                System.out.println("Marked as new! Awoo!");
                System.out.println(theList.get(target).getTaskId() + ".[" + theList.get(target).getStatusIcon() + "] " + theList.get(target).getTask());
            }

        } else { // Add item
            System.out.println("----------------");
            System.out.println("Added: " + text);
            theList.add(new Task(text));
            System.out.println("----------------");
        }
        return true;

    }

    public static void main(String[] args) {
        ArrayList<Task> theList = new ArrayList<>();
        // Greet the user
        greet();
        // Initiate Echo
        while (echo(theList)) {
            // While echo is true
        }
        System.out.println("----------------");
        System.out.println("See you soon! Bark!\n");

    }
}