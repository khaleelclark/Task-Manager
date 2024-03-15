import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

// once you enter the name show current task list, prompt do you want to see completed.
public class TaskList {
    static Scanner scanner = new Scanner(System.in);
    private ArrayList<Task> currentTaskList;
    private ArrayList<Task> completedTaskList;
    private String name;

    public TaskList(String name){
        this.name = name;
        this.currentTaskList = new ArrayList<>();
        this.completedTaskList = new ArrayList<>();
    }
    public String getName() {
        return name;
    }
    public ArrayList<Task> getCurrentTaskList(){
        return currentTaskList;
    }
    public ArrayList<Task> getCompletedTaskList(){
        return completedTaskList;
    }
    public void addCurrentTasks(Task task){
        currentTaskList.add(task);
    }
    public void addCompleteTasks(Task task){
        completedTaskList.add(task);
    }
    public void removeCurrentTask(int index){
        currentTaskList.remove(index);
    }
    public void removeCompletedTask(int index){
        completedTaskList.remove(index);
    }




}