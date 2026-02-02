package spark;

import java.io.FileNotFoundException; // Necessary for error handling

import spark.storage.Storage;
import spark.tasks.TaskList;
import spark.ui.Ui;

/**
 * A-MoreOOP
 * Made project more OOP!
 * The main class for the Spark application.
 * It initializes the necessary components and starts the user interface.
 */
public class Spark {
    private final Ui ui;
    private TaskList tasks;
    private static final String DATA_PATH = "./data/spark.txt";

    /**
     * Constructor for Spark class.
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
    public void start() {
        ui.start();
    }

    /**
     * The main method to run the Spark application.
     *
     * @param args Command-line arguments (not used).
     * @throws FileNotFoundException If the storage file is not found during initialization.
     */
    public static void main(String[] args) throws FileNotFoundException {
        new Spark(DATA_PATH).start();
    }
}