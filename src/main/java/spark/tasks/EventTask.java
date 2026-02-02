package spark.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Level 4
 * Event Task
 * Represents an event task with a description, start date, and end date.
 * Inherits from the Task class.
 */
public class EventTask extends Task {
    String description;
    LocalDateTime startDate;
    LocalDateTime endDate;
    // Start Date
    // Start Date Time
    // End Date
    // End Date Time

    /**
     * Constructor for EventTask class.
     * @param description The description of the event task.
     * @param startDate The start date of the event task in "yyyy-MM-dd HHmm" format.
     * @param endDate The end date of the event task in "yyyy-MM-dd HHmm" format.
     */
    public EventTask(String description, String startDate, String endDate) {
        super(description);
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.startDate = LocalDateTime.parse(startDate, inputFormatter);
        this.endDate = LocalDateTime.parse(endDate, inputFormatter);
        this.type = "E";
    }

    /**
     * Gets the start date in ISO format.
     * @return The start date as a string in "yyyy-MM-dd HHmm" format
     */
    public String getStartDateIso() {
        return this.startDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Gets the end date in ISO format.
     * @return The end date as a string in "yyyy-MM-dd HHmm" format
     */
    public String getEndDateIso() {
        return this.endDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    @Override
    public String getTaskInfo() {
        DateTimeFormatter printer = DateTimeFormatter.ofPattern("MMM d yyyy, h:mma");
        return super.getTaskInfo() + " (from: " + startDate.format(printer) + " to: " + endDate.format(printer) + ")";
    }
}
