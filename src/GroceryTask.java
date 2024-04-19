public class GroceryTask extends Task {
    private final int quantity;

    public GroceryTask(String taskName, int quantity) {
        super(taskName);
        this.quantity = quantity;
    }
    @Override
    public void printTaskInfo() {
        System.out.println(this.quantity + " " + this.getTaskName());
    }
}