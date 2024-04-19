public class RecurringTask extends Task {

    private final int frequency;
    public RecurringTask(String taskName, int frequency) {
        super(taskName);
        this.frequency = frequency;
    }
    @Override
    public void printTaskInfo() {
        System.out.println(super.getTaskName() + " repeats every " + this.frequency + " minutes.");
    }
}