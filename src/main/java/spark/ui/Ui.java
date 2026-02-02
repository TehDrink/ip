package spark.ui;
import spark.commands.Parser;
import spark.exceptions.SparkException;
import spark.tasks.TaskList;

import java.io.IOException;
import java.util.Scanner;

/**
 * Handles user interactions, including displaying messages and receiving input.
 * The Ui class provides methods to show welcome messages, error messages,
 * and to read user commands from the console.
 */
public class Ui {
    TaskList taskList;

    /**
     * Constructor for Ui class.
     * @param taskList The TaskList object to be used for displaying tasks.
     */
    public Ui(TaskList taskList) {
        // Receive input
        // Send to parser
        this.taskList = taskList;
    }

    /**
     * Level-0
     * Rename, Greet, Exit
     * Starts the main interaction loop of the application.
     * Continuously reads user input, sends it to the Parser, and displays the result
     * until termination command is received.
     */
    public void start() {
        Scanner sc = new Scanner(System.in);
        String output = "";

        this.greet();

        while (output != null) {
            // Parse each line
            try {
                if (!sc.hasNextLine()) {
                    break;
                }
                Parser parser = new Parser(sc.nextLine());
                output = parser.parse(taskList);

                if (output != null) {
                    System.out.println("----------------");
                    System.out.println(output);
                    System.out.println("----------------");
                }

            } catch (SparkException | IOException e) {
                System.out.println("----------------");
                System.out.println("Error Sniffed! " + e.getMessage());
                System.out.println("----------------");
            }
        }

        // Say Goodbye
        System.out.println("----------------");
        System.out.println("See you soon! Bark!\n");
    }

    /**
     * Displays a greeting message to the user.
     */
    public void greet() {
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

}
