public abstract class Task {
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

    public boolean getTaskStatus() {
        return taskStatus;
    }

    public void setTaskIncomplete() {
        this.taskStatus = false;
    }

    public void setTaskComplete(){
        this.taskStatus = true;
    }
    public abstract void printTaskInfo();
}