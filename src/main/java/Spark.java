import java.util.ArrayList;
import java.util.Scanner;  // Import the Scanner class


// Level-2
// Add, List
class ListItem {
    String itemDescription; // Item Description
    ListItem(String itemDescription) {
        this.itemDescription = itemDescription;
    }
}

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

    // Level-1 & Level-2
    // Echo
    // Add, List
    public static boolean echo(ArrayList<ListItem> theList) {
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();

        if (text.equals("bye")) {
            return false;

        } else if (text.equals("list")) {
            System.out.println("----------------");
            for (int i = 0; i < theList.size(); i++) {
                System.out.println(i+1 + ". " + theList.get(i).itemDescription);
            }
            System.out.println("----------------");

        } else { // Add item
            System.out.println("----------------");
            System.out.println("Added: " + text);
            theList.add(new ListItem(text));
            System.out.println("----------------");

        }
        return true;

    }

    public static void main(String[] args) {
        ArrayList<ListItem> theList = new ArrayList<>();
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