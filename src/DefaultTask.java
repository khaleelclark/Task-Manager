public class DefaultTask extends Task {

    public DefaultTask(String taskName) {
        super(taskName);
    }
    @Override
    public void printTaskInfo() {
        System.out.println(super.getTaskName() + " is " + getTaskStatus());
    }
}