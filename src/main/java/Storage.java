import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String path) {
        this.filePath = path;
    }

    // Level 7
    // Save
    public void saveTasks(ArrayList<Task> taskList) throws IOException {
        try {
            File folder = new File("./data");

            if (!folder.exists()) {
                folder.mkdirs();
            }

            try (FileWriter fw = new FileWriter(filePath)) {

                for (Task task : taskList) {
                    int status = task.getStatusIcon().equals("X") ? 1 : 0;

                    String line = task.getType() + " | " + status + " | " + task.description;

                    if (task instanceof EventTask) {
                        line += " | " + ((EventTask) task).getStartDateIso() + " | " + ((EventTask) task).getEndDateIso();
                    } else if (task instanceof DeadlineTask) {
                        line += " | " + ((DeadlineTask) task).getDeadlineIso();
                    }

                    fw.write(line + System.lineSeparator());
                }
            }

        } catch (IOException e) {
            System.out.println("Bark! I couldn't save my memory: " + e.getMessage());
        }
    }

    public ArrayList<Task> loadTasks() throws FileNotFoundException {
        ArrayList<Task> taskList = new ArrayList<>();
        File f = new File(filePath);
        if (!f.exists()) return new ArrayList<Task>();

        Scanner sc = new Scanner(f);

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] parts = line.split(" \\| ");

            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];

            Task task;

            switch (type) {
                case "T":
                    task = new Task(description);
                    break;
                case "D":
                    String by = parts[3];
                    task = new DeadlineTask(description, by);
                    break;
                case "E":
                    String from = parts[3];
                    String to = parts[4];
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

        sc.close();
        return taskList;
    }

}
