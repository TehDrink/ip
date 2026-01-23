// Level 3
// Mark as Done
public class Task {

    String description;
    boolean isDone;
    int taskId = 1;
    static int counter = 1;

    public Task(String description){
        this.description = description;
        this.isDone = false;
        this.taskId = counter++;
    }

    public String getTask(){
        return description;
    }

    public int getTaskId(){
        return this.taskId;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone(){
        this.isDone = true;
    }

    public void markAsNew(){
        this.isDone = false;
    }

}
