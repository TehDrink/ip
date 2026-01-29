import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {
    LocalDateTime endDate;

    //    String endTime;
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

    public String getDeadlineIso() {
        return this.endDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }
}
