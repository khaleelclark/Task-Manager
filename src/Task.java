public class Task {
    private String taskName;
    private boolean taskStatus;

    public Task (String taskName){
        this.taskName = taskName;
        // set task status to incomplete by default
        this.taskStatus = false;
    }
    public String getTaskName() {
        return taskName;
    }
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
    public void complete(){
        this.taskStatus = true;
    }
    @Override
    public String toString() {
        return taskName + " (Status: " + (taskStatus ? "Complete" : "Incomplete") + ")";
    }
}
