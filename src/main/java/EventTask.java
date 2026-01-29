import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {
    String description;
    LocalDateTime startDate;
    LocalDateTime endDate;
    // Start Date
    // Start Date Time
    // End Date
    // End Date Time

    public EventTask(String description, String startDate, String endDate) {
        super(description);
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.startDate = LocalDateTime.parse(startDate, inputFormatter);
        this.endDate = LocalDateTime.parse(endDate, inputFormatter);
        this.type = "E";
    }

    public String getStartDateIso() {
        return this.startDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    public String getEndDateIso() {
        return this.endDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    @Override
    public String getTaskInfo() {
        DateTimeFormatter printer = DateTimeFormatter.ofPattern("MMM d yyyy, h:mma");
        return super.getTaskInfo() + " (from: " + startDate.format(printer) + " to: " + endDate.format(printer) + ")";
    }
}
