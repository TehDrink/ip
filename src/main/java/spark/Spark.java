package spark;

import java.io.FileNotFoundException; // Necessary for error handling

import spark.storage.Storage;
import spark.tasks.TaskList;
import spark.ui.Ui;

/**
 * Initializes the necessary components and starts the user interface.
 * The main class for the Spark application.
 * A-MoreOOP
 * Made project more OOP!
 */
public class Spark {
    private final Ui ui;
    private TaskList tasks;

    /**
     * Constructs the Spark class. (Constructor)
     * Initializes the Storage, TaskList, and Ui components.
     *
     * @throws FileNotFoundException If the storage file is not found during TaskList initialization.
     */
    public Spark(String filePath) throws FileNotFoundException {
        Storage storage = new Storage(filePath);
        // Load existing data
        tasks = new TaskList(storage);
        ui = new Ui(tasks);
    }

    /**
     * Starts the Spark application by invoking the UI's start method.
     */
    public String run(String input) {
        return ui.run(input);
    }

    /**
     * Returns the greeting message from the UI.
     *
     * @return A string containing the greeting message.
     */
    public String getGreeting() {
        return ui.sayGreeting();
    }

    /**
     * Returns the goodbye message from the UI.
     *
     * @return A string containing the goodbye message.
     */
    public String getGoodbye() {
        return ui.sayGoodbye();
    }

}
