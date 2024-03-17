import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskManagerV2 {
    static List<TaskList> taskListList = new ArrayList<>();
    static TaskList selectedTaskList;
    static Scanner scanner = new Scanner(System.in);
    static boolean start = true;

    public static void main(String[] args) {
        startTaskManager();
    }
    //user defined methods below
    public static void startTaskManager(){
        System.out.println("Welcome to Zindel's Task Manager!");

        //user options to access task list operations
        while(start) {
            System.out.println("Please select from the following options:\n");
            System.out.println("1. Add a new task list");
            System.out.println("2. View task lists");
            System.out.println("3. Select a task list");
            System.out.println("4. Remove a task list");
            System.out.println("5. Exit the task manager");

            String initialUserInput = scanner.nextLine();
            switch (initialUserInput) {
                case "1" -> addNewTaskList();
                case "2" -> {
                    if(taskListList.isEmpty()){
                        System.out.println("You have no task lists to view. Add a new list now!\n");
                    } else {
                        System.out.println("Your current task lists are: ");
                        displayTaskListIndex();
                    }
                }
                case "3" ->  {
                    if(taskListList.isEmpty()){
                        System.out.println("You have no task lists to select. Add a new list now!\n");
                    } else {
                        start = selectTaskList();
                    }
                }
                case "4" -> {
                    if(taskListList.isEmpty()){
                        System.out.println("You have no task lists to remove. Add a new list now!\n");
                    } else {
                        removeTaskList();
                    }
                }
                case "5" -> {
                    endTaskManager();
                    return;
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
            System.out.println("6. Change a task name");
            System.out.println("7. Switch task list");
            System.out.println("9. Switch to previous menu");
            System.out.println("8. Exit the Task Manager");

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
                case "6" -> changeTaskName();
                case "7" -> selectTaskListToSwitch();
                case "8" -> {
                    endTaskManager();
                    return;
                }
                default -> System.out.println("Error: Invalid Entry. Please try again\n");
            }
        }
    }
    public static void addTasks(){
        System.out.println("Please enter your task: ");
        String taskName = scanner.nextLine();

        //create a new instance of the task object using the user input to set the name
        Task newTask = new Task(taskName);

        //add the new Task object to the list
        selectedTaskList.getCurrentTaskList().add(newTask);
        System.out.println("Task: " + newTask.getTaskName() + " Added successfully!\n");
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
    public static void changeTaskName(){
        System.out.println("Please enter the number of which task name you'd like to edit: ");
        displayCurrentTaskIndex();
        int taskIndexToEdit = Integer.parseInt(scanner.nextLine());
        if (taskIndexToEdit >= 0 && taskIndexToEdit < selectedTaskList.getCurrentTaskList().size()){
            Task taskToEdit = selectedTaskList.getCurrentTaskList().get(taskIndexToEdit);
            System.out.println("Current task name: " + taskToEdit.getTaskName());
            System.out.println("Please enter a new task name: ");
            String newTaskName = scanner.nextLine();
            taskToEdit.setTaskName(newTaskName);
            System.out.println("Task " + taskToEdit.getTaskName() + " updated successfully!");
        } else {
            System.out.println("Invalid task number. Please try again.\n");
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
       System.out.println("The selected task list has been removed.");
    }
    public static boolean selectTaskList(){
        System.out.println("Please enter the index of the task list you'd like to Select");
        displayTaskListIndex();
        int indexToSelect = Integer.parseInt(scanner.nextLine());
        if (indexToSelect >= 0 && indexToSelect < taskListList.size()){
            selectedTaskList = taskListList.get(indexToSelect);
            System.out.println("You have selected the task list: " + selectedTaskList.getName());
            return false;
        } else System.out.println("Invalid entry. Please enter a valid number.\n");
        return true;
    }
    public static void selectTaskListToSwitch(){
        System.out.println("Please select the number of which task list you'd like to switch to: ");
        displayTaskListIndex();
        int indexToSwitchList = Integer.parseInt(scanner.nextLine());
        if (indexToSwitchList >= 0 && indexToSwitchList < taskListList.size()){
            selectedTaskList = taskListList.get(indexToSwitchList);
            System.out.println("Switched to task list: " + selectedTaskList.getName());
        } else System.out.println("Invalid entry. Please try again.\n");
    }
}


