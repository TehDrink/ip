import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> taskList;
    Storage storage;

    public TaskList(Storage storage) throws FileNotFoundException {
        this.taskList = storage.loadTasks();
        this.storage = storage;
    }

    public void addTask(Task task) throws IOException {
        this.taskList.add(task);
        this.storage.saveTasks(this.taskList);
    }

    public void removeTask(int index) throws IOException {
        taskList.remove(index);
        this.storage.saveTasks(this.taskList);
    }

    public Task getTask(int index) throws IOException {
        return taskList.get(index);
    }

    public int size() {
        return taskList.size();
    }

}
