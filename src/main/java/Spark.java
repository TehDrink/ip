import java.util.Objects;
import java.util.Scanner;  // Import the Scanner class

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

    // Level-1
    // Echo
    public static boolean echo() {
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();

        if (text.equals("bye")) {
            return false;
        } else {
            System.out.println("----------------");
            System.out.println(text);
            System.out.println("----------------");

            return true;
        }
    }

    public static void main(String[] args) {
        // Greet the user
        greet();
        // Initiate Echo
        while (echo()) {
            // While echo is true
        }
        System.out.println("----------------");
        System.out.println("See you soon! Bark!\n");

    }
}
