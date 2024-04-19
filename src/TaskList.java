import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> TaskList;

    private final String name;

    public TaskList(String name){
        this.name = name;
        this.TaskList = new ArrayList<>();
    }
    public String getName() {
        return name;
    }
    public ArrayList<Task> getTaskList(){
        return TaskList;
    }
}