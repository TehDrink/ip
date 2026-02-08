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
    private static final String DATA_PATH = "./data/spark.txt";
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
    public void start() {
        ui.start();
    }

    /**
     * Runs the Spark application.
     *
     * @param args Command-line arguments (not used).
     * @throws FileNotFoundException If the storage file is not found during initialization.
     */
    public static void main(String[] args) throws FileNotFoundException {
        new Spark(DATA_PATH).start();
    }
}
