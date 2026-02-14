package spark.tasks;

import java.time.format.DateTimeFormatter;

/**
 * Represents a generic task with a description and completion status.
 * The Task class serves as a base class for specific task types like Task (Todo), Deadline, and Event.
 */
public class Task {
    public static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    public static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy, h:mma");
    private static int counter = 1;
    protected String type;
    private String description;
    private boolean isDone;
    private int taskId;

    /**
     * Constructs the Task class. (Constructor)
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        assert description != null : "Description should never be null";
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
     * Sets the description of the task.
     *
     * @param newDescription The new description to set for the task.
     */
    public void setDescription(String newDescription) {
        assert newDescription != null : "New description should never be null";
        this.description = newDescription;
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
     * Marks the task as done.
     * Level 3
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     * Level 3
     */
    public void markAsNew() {
        this.isDone = false;
    }

}
