package spark.tasks;

import spark.storage.Storage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
/**
 * Manages a list of tasks, providing methods to manipulate and access them. (Addition, Deletion, Retrieval)
 * It coordinates with the Storage class to ensure tasks are saved and loaded from persistent storage.
 */
public class TaskList {
    ArrayList<Task> taskList;
    Storage storage;
    /**
     * Constructor for TaskList class.
     * Initializes the task list by loading tasks from the provided Storage object.
     * @param storage The Storage object used to load and save tasks.
     * @throws FileNotFoundException If the storage file is not found during loading.
     */
    public TaskList(Storage storage) throws FileNotFoundException {
        this.taskList = storage.loadTasks();
        this.storage = storage;
    }

    /**
     * Adds a new task to the task list and saves the updated list to storage.
     * @param task The Task object to be added.
     * @throws IOException If there is an error saving the updated task list.
     */
    public void addTask(Task task) throws IOException {
        this.taskList.add(task);
        this.storage.saveTasks(this.taskList);
    }

    /**
     * Deletes a task from the task list at the specified index and saves the updated list to storage.
     * @param index The index of the task to be deleted.
     * @throws IOException If there is an error saving the updated task list.
     */
    public void removeTask(int index) throws IOException {
        taskList.remove(index);
        this.storage.saveTasks(this.taskList);
    }

    /**
     * Retrieves the task at the specified index from the task list.
     * @param index The index of the task to retrieve.
     * @return The Task object at the specified index.
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Returns the number of tasks currently in the task list.
     * @return The size of the task list.
     */
    public int size() {
        return taskList.size();
    }

}
