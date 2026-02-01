import java.io.FileNotFoundException; // Necessary for error handling

// A-MoreOOP
// Made project more OOP!
public class Spark {
    private final Ui ui;
    private TaskList tasks;
    private static final String DATA_PATH = "./data/spark.txt";

    public Spark(String filePath) throws FileNotFoundException {
        Storage storage = new Storage(filePath);
        // Load existing data
        tasks = new TaskList(storage);
        ui = new Ui(tasks);
    }

    // Start all processes
    public void start() {
        ui.start();
    }

    public static void main(String[] args) throws FileNotFoundException {
        new Spark(DATA_PATH).start();
    }
}