import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskManagerV2 {
    static List<Task> currentTaskList = new ArrayList<>();
    static List<Task> completedTaskList = new ArrayList<>();

    //static ArrayList<String> currentTaskList = new ArrayList<>();
    //static ArrayList<String> completedTaskList = new ArrayList<>();
    public static void main(String[] args) {
        startTaskManager();
    }
    //user defined methods below

    // This method holds the user interaction for the Task manager
    public static void startTaskManager(){
        System.out.println("Welcome to Zindel's Task Manager!");
        while (true) {
            System.out.println("Please select from the following options:\n");
            System.out.println("1. Add a new task");
            System.out.println("2. View current tasks");
            System.out.println("3. Complete a task");
            System.out.println("4. View completed tasks");
            System.out.println("5. Remove a task");
            System.out.println("6. Exit the Task Manager");

            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine();
            switch (userInput) {
                case "1" -> addTasks();
                case "2" -> viewTasks();
                case "3" -> {
                    if(currentTaskList.isEmpty()){
                        System.out.println("You have no tasks to complete. Add some tasks!\n");
                        break;
                    } else displayCurrentTaskIndex();
                    System.out.print("Enter the number of the task you'd like to complete: ");
                    int indexToComplete = scanner.nextInt();
                    scanner.nextLine();
                    completeTaskByIndex(indexToComplete);
                }
                case "4" -> completedTasks();
                case "5" -> {
                    if(currentTaskList.isEmpty()){
                        System.out.println("You have no tasks to remove. Add some tasks!\n");
                        break;
                    } else displayCurrentTaskIndex();
                    System.out.print("Enter the number of the task you'd like to remove: ");
                    int indexToRemove = scanner.nextInt();
                    scanner.nextLine();
                    removeTaskByIndex(indexToRemove);
                }
                case "6" -> {
                    endTaskManager();
                    return;
                }
                default -> System.out.println("Error: Invalid Entry. Please try again\n");
            }

        }
    }
    // This method prompts the user to enter their task, then stores it in the current task list array,
    // and prints a confirmation with the task name

    public static void addTasks(){
        System.out.println("Please enter your task: ");
        Scanner scanner = new Scanner(System.in);
        String taskName = scanner.nextLine();

        // set task status to incomplete by default
        boolean taskStatus = false;

        //create a new instance of the task object using the user input to set the name
        Task newTask = new Task(taskName, taskStatus);

        //add the new Task object to the list
        currentTaskList.add(newTask);
        System.out.println("Task: " + taskName + " Added successfully!\n");
    }

    public static void viewTasks(){
        if(currentTaskList.isEmpty()){
            System.out.println("You're all caught up!");
        } else {
            System.out.println("Your current tasks are: ");
            for (Task task : currentTaskList) {
                System.out.println(task);
            }
            System.out.println("\n");
        }
    }

    public static void completedTasks(){
        if(completedTaskList.isEmpty()){
            System.out.println("You have no completed tasks. Complete a task and let's get things done!");
        } else {
            System.out.println("Your current completed tasks are: ");
            for (Task task : completedTaskList) {
                System.out.println(task);
            }
            System.out.println(" ");
        }
    }
    public static void displayCurrentTaskIndex(){
        int index = 0;
        for (Task task : currentTaskList){
            System.out.println(index + ". " + task);
            index++;
        }
    }
    public static void removeTaskByIndex(int indexToRemove){
        if (indexToRemove >= 0 && indexToRemove < currentTaskList.size()){
            System.out.println("Task: " + currentTaskList.get(indexToRemove));
            Task removeTask = currentTaskList.remove(indexToRemove);
            System.out.println("Task: " + removeTask + " Has been removed.\n");
        } else {
            System.out.println("Invalid entry. Please try again.\n");
        }
    }
    public static void completeTaskByIndex(int indexToComplete){
        if ( indexToComplete >= 0 &&  indexToComplete < currentTaskList.size()){
            Task taskToComplete = currentTaskList.get(indexToComplete);
            taskToComplete.setTaskStatus(true);
            completedTaskList.add(taskToComplete);
            System.out.println("Task: " + taskToComplete + "\n");
            currentTaskList.remove(indexToComplete);
        } else {
            System.out.println("Invalid entry. Please try again.\n");
        }
    }
    public static void endTaskManager(){
        System.out.println("Thank you for using Zindel's Task Manager");
        System.out.println("Goodbye!");
    }
}


