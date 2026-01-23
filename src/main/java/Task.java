// Level 3
// Mark as Done
public class Task {

    String description;
    String type;
    boolean isDone;
    int taskId = 1;
    static int counter = 1;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.type = "T";
        this.taskId = counter++;
    }

    public String getTaskInfo() {
        return this.getTaskId() + ". [" + this.getType() + "][" + this.getStatusIcon() + "] " + description;
    }

    public int getTaskId() {
        return this.taskId;
    }

    public String getType() {
        return this.type;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNew() {
        this.isDone = false;
    }

}
