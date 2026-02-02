package spark.tasks;

/**
 * Represents a generic task with a description and completion status.
 * The Task class serves as a base class for specific task types like Task (Todo), Deadline, and Event.
 */
public class Task {

    private String description;
    protected String type;
    private boolean isDone;
    private int taskId = 1;
    private static int counter = 1;

    /**
     * Constructor for Task class.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.type = "T";
        this.taskId = counter++;
    }

    /**
     * Gets the task info in a formatted string.
     *
     * @return The formatted task information string.
     */
    public String getTaskInfo() {
        return "[" + this.getType() + "][" + this.getStatusIcon() + "] " + description;
    }

    /**
     * Gets the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Gets the id of the task.
     *
     * @return The task id as an integer.
     */
    public int getTaskId() {
        return this.taskId;
    }

    /**
     * Gets the type of the task.
     */
    public String getType() {
        return this.type;
    }

    /**
     * Gets the status icon representing whether the task is done.
     *
     * @return "X" if the task is done, otherwise a space " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Level 3
     * Mark as Done
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Level 3
     * Mark as not Done
     * Marks the task as not done.
     */
    public void markAsNew() {
        this.isDone = false;
    }

}
