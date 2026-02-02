package spark.tasks;

import spark.storage.Storage;

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

    /**
     * find the tasks that contain the keyword
     * @param keyword keyword to search for
     * @return list of tasks that contain the keyword
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<Task>();
        for (Task task : taskList) {
            if (task.getDescription().contains(keyword)) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public int size() {
        return taskList.size();
    }

}
