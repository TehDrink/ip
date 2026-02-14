package spark.tasks;

import java.time.LocalDateTime;

/**
 * Level 4
 * Deadline Task
 * Represents a deadline task with a description and a due date.
 * Inherits from the Task class.
 */
public class DeadlineTask extends Task {
    private LocalDateTime endDate;

    /**
     * Constructs the DeadlineTask class. (Constructor)
     *
     * @param description The description of the deadline task.
     * @param endDate     The due date of the deadline task in "yyyy-MM-dd HHmm" format.
     */
    public DeadlineTask(String description, String endDate) {
        super(description);
        this.type = "D";
        this.endDate = LocalDateTime.parse(endDate, Task.INPUT_FORMAT);
    }

    /**
     * Sets the end date of the deadline task using an ISO formatted string.
     *
     * @param endDate The new end date in "yyyy-MM-dd HHmm" format.
     */
    public void setEndDateIso(String endDate) {
        this.endDate = LocalDateTime.parse(endDate, Task.INPUT_FORMAT);
    }

    @Override
    public String getTaskInfo() {
        String formattedDate = this.endDate.format(Task.OUTPUT_FORMAT);
        return super.getTaskInfo() + " (by: " + formattedDate + ")";
    }

    /**
     * Gets the end date in ISO format.
     *
     * @return The end date as a string in "yyyy-MM-dd HHmm" format.
     */
    public String getDeadlineIso() {
        return this.endDate.format(Task.INPUT_FORMAT);
    }
}
