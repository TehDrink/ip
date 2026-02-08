package spark.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import spark.tasks.DeadlineTask;
import spark.tasks.EventTask;
import spark.tasks.Task;

/**
 * Manages the loading and saving of tasks to a persistent storage file.
 * The Storage class handles reading tasks from a file during initialization
 * and writing tasks back to the file when changes occur.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs the Storage class. (Constructor)
     *
     * @param filePath The path to the file used for storing tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    // Level 7
    // Save

    /**
     * Saves the current list of tasks to the storage file.
     * The method writes each task in a specific pipe-separated format.
     *
     * @param taskList The ArrayList of Task objects to be saved.
     */
    public void saveTasks(ArrayList<Task> taskList) {
        try {
            File folder = new File("./data");

            if (!folder.exists()) {
                folder.mkdirs();
            }

            try (FileWriter fileWriter = new FileWriter(filePath)) {

                for (Task task : taskList) {
                    int status = task.getStatusIcon().equals("X") ? 1 : 0;

                    String line = task.getType() + " | " + status + " | " + task.getDescription();

                    if (task instanceof EventTask) {
                        line += " | " + ((EventTask) task).getStartDateIso() + " | "
                                + ((EventTask) task).getEndDateIso();
                    } else if (task instanceof DeadlineTask) {
                        line += " | " + ((DeadlineTask) task).getDeadlineIso();
                    }

                    fileWriter.write(line + System.lineSeparator());
                }
            }

        } catch (IOException e) {
            System.out.println("Bark! I couldn't save my memory: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the storage file into an ArrayList by parsing pipe-separated values.
     * The method reads each line from the file, parses it, and creates Task objects accordingly.
     *
     * @return An ArrayList of Task objects loaded from the file.
     *         Return empty list if file does not exist or no items in list.
     * @throws FileNotFoundException If the storage file does not exist.
     */
    public ArrayList<Task> loadTasks() throws FileNotFoundException {
        ArrayList<Task> taskList = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return new ArrayList<Task>();
        }

        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] splitInput = line.split(" \\| ");

            String type = splitInput[0];
            boolean isDone = splitInput[1].equals("1");
            String description = splitInput[2];

            Task task;

            switch (type) {
            case "T":
                task = new Task(description);
                break;
            case "D":
                String by = splitInput[3];
                task = new DeadlineTask(description, by);
                break;
            case "E":
                String from = splitInput[3];
                String to = splitInput[4];
                task = new EventTask(description, from, to);
                break;
            default:
                continue; // Skip unknown formats
            }

            if (isDone) {
                task.markAsDone();
            }

            taskList.add(task);
        }

        scanner.close();
        return taskList;
    }

}
