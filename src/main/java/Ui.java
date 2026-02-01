import java.io.IOException;
import java.util.Scanner;

// Deal with interactions with user
public class Ui {
    TaskList taskList;

    public Ui(TaskList taskList) {
        // Receive input
        // Send to parser
        this.taskList = taskList;
    }

    // Level-0
    // Rename, Greet, Exit
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
                System.out.println("----------------");
                output = parser.parse(taskList);
                System.out.println(output);
                System.out.println("----------------");

            } catch (SparkException | IOException e) {
                System.out.println("Error Sniffed! " + e.getMessage());
            }
        }

        // Say Goodbye
        System.out.println("----------------");
        System.out.println("See you soon! Bark!\n");
    }


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
