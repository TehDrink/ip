package spark.tasks;

import java.time.LocalDateTime;

/**
 * Level 4
 * Event Task
 * Represents an event task with a description, start date, and end date.
 * Inherits from the Task class.
 */
public class EventTask extends Task {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    /**
     * Constructor for EventTask class.
     *
     * @param description The description of the event task.
     * @param startDate   The start date of the event task in "yyyy-MM-dd HHmm" format.
     * @param endDate     The end date of the event task in "yyyy-MM-dd HHmm" format.
     */
    public EventTask(String description, String startDate, String endDate) {
        super(description);
        this.startDate = LocalDateTime.parse(startDate, Task.INPUT_FORMAT);
        this.endDate = LocalDateTime.parse(endDate, Task.INPUT_FORMAT);
        this.type = "E";
    }

    /**
     * Gets the start date in ISO format.
     *
     * @return The start date as a string in "yyyy-MM-dd HHmm" format
     */
    public String getStartDateIso() {
        return this.startDate.format(Task.INPUT_FORMAT);
    }

    /**
     * Gets the end date in ISO format.
     *
     * @return The end date as a string in "yyyy-MM-dd HHmm" format
     */
    public String getEndDateIso() {
        return this.endDate.format(Task.INPUT_FORMAT);
    }

    public void setStartDateIso(String startDate) {
        this.startDate = LocalDateTime.parse(startDate, Task.INPUT_FORMAT);
    }

    public void setEndDateIso(String endDate) {
        this.endDate = LocalDateTime.parse(endDate, Task.INPUT_FORMAT);
    }

    @Override
    public String getTaskInfo() {
        return super.getTaskInfo()
                + " (from: " + startDate.format(Task.OUTPUT_FORMAT)
                + " to: " + endDate.format(Task.OUTPUT_FORMAT)
                + ")";
    }

}
