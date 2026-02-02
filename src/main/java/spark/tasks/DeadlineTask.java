package spark.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Level 4
 * Deadline Task
 * Represents a deadline task with a description and a due date.
 * Inherits from the Task class.
 */
public class DeadlineTask extends Task {
    private LocalDateTime endDate;

    /**
     * Constructor for DeadlineTask class.
     *
     * @param description The description of the deadline task.
     * @param endDate     The due date of the deadline task in "yyyy-MM-dd HHmm" format.
     */
    public DeadlineTask(String description, String endDate) {
        super(description);
        this.type = "D";
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.endDate = LocalDateTime.parse(endDate, inputFormatter);
    }


    @Override
    public String getTaskInfo() {
        String formattedDate = this.endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mma"));
        return super.getTaskInfo() + " (by: " + formattedDate + ")";
    }

    /**
     * Gets the end date in ISO format.
     *
     * @return The end date as a string in "yyyy-MM-dd HHmm" format.
     */
    public String getDeadlineIso() {
        return this.endDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }
}
