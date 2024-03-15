import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskManagerV2 {
    static List<TaskList> taskListList = new ArrayList<>();
    static TaskList selectedTaskList;
    static Scanner scanner = new Scanner(System.in);
    static boolean temp = true;

    public static void main(String[] args) {
        startTaskManager();
    }
    //user defined methods below
    public static void startTaskManager(){
        System.out.println("Welcome to Zindel's Task Manager!");

        //user options to access task list operations


        while(temp) {
            System.out.println("Please select from the following options:\n");
            System.out.println("1. Add a new task list");
            System.out.println("2. View task lists");
            System.out.println("3. Select a task list");
            System.out.println("4. Remove a task list");

            String initialUserInput = scanner.nextLine();
            switch (initialUserInput) {
                case "1" -> addNewTaskList();
                case "2" -> {
                    if(taskListList.isEmpty()){
                        System.out.println("You have no task lists to view. Add a new list now!\n");
                    } else {
                        System.out.println("Your current task lists are: ");
                        displayTaskListIndex();
                        System.out.println("");
                    }
                }
                case "3" ->  {
                    if(taskListList.isEmpty()){
                        System.out.println("You have no task lists to select. Add a new list now!\n");
                    } else {
                        temp = selectTaskList();
                    }
                }
                case "4" -> {
                    if(taskListList.isEmpty()){
                        System.out.println("You have no task lists to remove. Add a new list now!\n");
                    } else {
                        removeTaskList();
                    }
                }
                default -> System.out.println("Error: Invalid Entry. Please try again\n");
            }
        }
        //user options for task operations
        while (true) {
            System.out.println("Please select from the following options:\n");
            System.out.println("1. Add a new task");
            System.out.println("2. View current tasks");
            System.out.println("3. Complete a task");
            System.out.println("4. View completed tasks");
            System.out.println("5. Remove a task");
            System.out.println("6. Switch task list");
            System.out.println("7. Exit the Task Manager");

            String userInput = scanner.nextLine();
            switch (userInput) {
                case "1" -> addTasks();
                case "2" -> viewTasks();
                case "3" -> {
                    if(selectedTaskList.getCurrentTaskList().isEmpty()){
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
                    if(selectedTaskList.getCurrentTaskList().isEmpty()){
                        System.out.println("You have no tasks to remove. Add some tasks!\n");
                        break;
                    } else displayCurrentTaskIndex();
                    System.out.print("Enter the number of the task you'd like to remove: ");
                    int indexToRemove = scanner.nextInt();
                    scanner.nextLine();
                    removeTaskByIndex(indexToRemove);
                }
                case "6" -> {
                    selectTaskListToSwitch();
                }
                case "7" -> {
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
        String taskName = scanner.nextLine();

        //create a new instance of the task object using the user input to set the name
        Task newTask = new Task(taskName);

        //add the new Task object to the list
        selectedTaskList.getCurrentTaskList().add(newTask);
        System.out.println("Task: " + taskName + " Added successfully!\n");
    }

    public static void viewTasks(){
        if(selectedTaskList.getCurrentTaskList().isEmpty()){
            System.out.println("You're all caught up!");
        } else {
            System.out.println("Your current tasks are: ");
            for (Task task : selectedTaskList.getCurrentTaskList()) {
                System.out.println(task);
            }
            System.out.println("\n");
        }
    }

    public static void completedTasks(){
        if(selectedTaskList.getCompletedTaskList().isEmpty()){
            System.out.println("You have no completed tasks. Complete a task and let's get things done!");
        } else {
            System.out.println("Your current completed tasks are: ");
            for (Task task : selectedTaskList.getCompletedTaskList()) {
                System.out.println(task);
            }
            System.out.println(" ");
        }
    }
    public static void displayCurrentTaskIndex(){
        int index = 0;
        for (Task task : selectedTaskList.getCurrentTaskList()){
            System.out.println(index + ". " + task);
            index++;
        }
    }
    public static void removeTaskByIndex(int indexToRemove){
        if (indexToRemove >= 0 && indexToRemove < selectedTaskList.getCurrentTaskList().size()){
            System.out.println("Task: " + selectedTaskList.getCurrentTaskList().get(indexToRemove));
            Task removeTask = selectedTaskList.getCurrentTaskList().remove(indexToRemove);
            System.out.println("Task: " + removeTask + " Has been removed.\n");
        } else {
            System.out.println("Invalid entry. Please try again.\n");
        }
    }
    public static void completeTaskByIndex(int indexToComplete){
        if ( indexToComplete >= 0 &&  indexToComplete < selectedTaskList.getCurrentTaskList().size()){
            Task taskToComplete = selectedTaskList.getCurrentTaskList().get(indexToComplete);
            taskToComplete.complete();
            selectedTaskList.getCompletedTaskList().add(taskToComplete);
            System.out.println("Task: " + taskToComplete + "\n");
            selectedTaskList.getCurrentTaskList().remove(indexToComplete);
        } else {
            System.out.println("Invalid entry. Please try again.\n");
        }
    }
    public static void endTaskManager(){
        System.out.println("Thank you for using Zindel's Task Manager");
        System.out.println("Goodbye!");
    }

    //add task list method and remove task list method

    public static void addNewTaskList(){
        System.out.println("Please enter the name of your new Task List: ");
        String taskListName = scanner.nextLine();
        taskListList.add(new TaskList(taskListName));
    }
    public static void displayTaskListIndex(){
        int index = 0;
        for (TaskList list : taskListList){
            System.out.println(index + ". " + list.getName());
            index++;
        }
    }
    public static void removeTaskList(){
        System.out.println("Please enter the index of the task list you'd like to remove");
        displayTaskListIndex();
        int indexToRemove = Integer.parseInt(scanner.nextLine());
        if (indexToRemove > taskListList.size()){
            System.out.println("Invalid entry. Please try again.\n");
        } else taskListList.remove(indexToRemove);
    }
    public static boolean selectTaskList(){
        System.out.println("Please enter the index of the task list you'd like to Select");
        displayTaskListIndex();
        int indexToSelect = Integer.parseInt(scanner.nextLine());
        if (indexToSelect > taskListList.size()){
            System.out.println("Invalid entry. Please try again.\n");
        } else selectedTaskList = taskListList.get(indexToSelect);
        return false;
    }
    public static void selectTaskListToSwitch(){
        System.out.println("Please select which task list you'd like to switch to: ");
        displayTaskListIndex();
        int indexToSwitchList = Integer.parseInt(scanner.nextLine());
        if (indexToSwitchList > taskListList.size()){
            System.out.println("Invalid entry. Please try again.\n");
        } else selectedTaskList = taskListList.get(indexToSwitchList);
    }
}


