public class DeadlineTask extends Task {
    String endDate;

    //    String endTime;
    public DeadlineTask(String description, String endDate) {
        super(description);
        this.type = "D";
        this.endDate = endDate;
//        this.endTime = endTime;
    }

    public String getDeadline() {
        // Return
        return this.endDate;
    }

    @Override
    public String getTaskInfo() {
        return super.getTaskInfo() + " (by: " + this.getDeadline() + ")";
    }
}
