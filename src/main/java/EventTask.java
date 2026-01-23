public class EventTask extends DeadlineTask {
    String description;
    String startDate;

    // Start Date
    // Start Date Time
    // End Date
    // End Date Time

    public EventTask(String description, String startDate, String endDate) {
        super(description, endDate);
        this.description = description;
        this.startDate = startDate;
        this.type = "E";
    }

    public String getStartDate() {
        return this.startDate;
    }

    @Override
    public String getTaskInfo(){
        return this.getTaskId() + ". [" + this.getType() +"][" + this.getStatusIcon() + "] " + description + " (from: "+ this.getStartDate() +" to: " + this.getDeadline() +")";
    }
}
