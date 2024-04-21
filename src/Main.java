import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main {
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
            System.out.println("Welcome to the main menu. Please select from the following options:\n");
            System.out.println("1. Add a new task");
            System.out.println("2. View current tasks");
            System.out.println("3. Complete a task");
            System.out.println("4. View completed tasks");
            System.out.println("5. Remove a task");
            System.out.println("6. Change a task name");
            System.out.println("7. Switch task list");
            System.out.println("8. Exit the Task Manager");

            String userInput = scanner.nextLine();
            switch (userInput) {
                case "1" -> addTasks();
                case "2" -> viewTasks();
                case "3" -> {
                    if(hasNoIncompleteTasks(selectedTaskList)){
                        System.out.println("You have no tasks to complete. Add some tasks!\n");
                    }
                    else {
                        System.out.print("Enter the number of the task you'd like to complete: \n");
                        displayCurrentTaskIndex();
                        try {
                            int indexToComplete = Integer.parseInt(scanner.nextLine());
                            completeTaskByIndex(indexToComplete);
                        }
                        catch (NumberFormatException nfe) {
                            System.out.println("Invalid input. Please enter a valid number.");
                        }
                    }
                }
                case "4" -> completedTasks();
                case "5" -> {
                    if(selectedTaskList.getTaskList().isEmpty()){
                        System.out.println("You have no tasks to remove. Add some tasks!\n");
                    } else {
                        System.out.print("Enter the number of the task you'd like to remove: \n");
                        displayCurrentTaskIndex();
                        try {
                            int indexToRemove = Integer.parseInt(scanner.nextLine());
                            removeTaskByIndex(indexToRemove);
                        }
                        catch (NumberFormatException nfe){
                            System.out.println("Invalid input. Please enter a valid number.");
                        }
                    }
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
        System.out.println("Please select what kind of task you would like to create, or type " +
        "'exit' to return to the main menu.\")");
        System.out.println("1. Regular Task");
        System.out.println("2. Recurring Task");
        System.out.println("3. Grocery List Item");
        String userInput = scanner.nextLine();
        switch(userInput){
            case "1" -> {
                System.out.println("Please enter a task name: ");
                String taskName = scanner.nextLine();
                DefaultTask newTask = new DefaultTask(taskName);
                selectedTaskList.getTaskList().add(newTask);
                System.out.println("Task: " + newTask.getTaskName() + " Added successfully!\n");
            }
            case "2" -> {
                System.out.println("Please enter a task name: ");
                String taskName = scanner.nextLine();
                System.out.println("Please enter how often you would like this task to repeat in minutes: ");
                String recurringTime = scanner.nextLine();
                RecurringTask newTask = new RecurringTask(taskName, Integer.parseInt(recurringTime));
                selectedTaskList.getTaskList().add(newTask);
                System.out.println("Task: " + newTask.getTaskName() + " Added successfully!\n");
            }
            case "3" -> {
                System.out.println("Please enter a grocery item: ");
                String taskName = scanner.nextLine();
                System.out.println("Please enter item quantity: ");
                String quantity = scanner.nextLine();
                GroceryTask newTask = new GroceryTask(taskName, Integer.parseInt(quantity));
                selectedTaskList.getTaskList().add(newTask);
                System.out.println("Task: " + newTask.getTaskName() + " Added successfully!\n");
                //fix output for complete, and view completed

            }
            case "exit" -> System.out.println("Selection canceled by user.");

            default -> System.out.println("Error: Invalid Entry. Please try again\n");
        }

    }
    public static void viewTasks(){
        if(hasNoIncompleteTasks(selectedTaskList)){
            System.out.println("You're all caught up!");
        } else {
            System.out.println("Your current tasks are: ");
            for (Task task : selectedTaskList.getTaskList()) {
                if (!task.getTaskStatus()){
                    task.printTaskInfo();
                }
            }
            System.out.println("\n");
        }
    }
    public static void completedTasks(){
        if(hasNoCompletedTasks(selectedTaskList)){
            System.out.println("You have no completed tasks. Complete a task and let's get things done!");
        } else {
            System.out.println("Your current completed tasks are: ");
            for (Task task : selectedTaskList.getTaskList()) {
                if (task.getTaskStatus()){
                    System.out.println(task.getTaskName());
                    task.printTaskInfo();
                }
            }
            System.out.println(" ");
        }
    }
    public static void changeTaskName() {
        while (true) {
            System.out.println("Please enter the number of which task name you'd like to edit: ");
            displayCurrentTaskIndex();
            System.out.println("Or type 'exit' to return to the main menu.");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting to the main menu.");
                break;
            }
            try {
                int taskIndexToEdit = Integer.parseInt(input);
                if (taskIndexToEdit >= 0 && taskIndexToEdit < selectedTaskList.getTaskList().size()) {
                    Task taskToEdit = selectedTaskList.getTaskList().get(taskIndexToEdit);
                    System.out.println("Current task name: " + taskToEdit.getTaskName());
                    System.out.println("Please enter a new task name: ");
                    String newTaskName = scanner.nextLine();
                    taskToEdit.setTaskName(newTaskName);
                    System.out.println("Task " + taskToEdit.getTaskName() + " updated successfully!");
                    break;
                } else {
                    System.out.println("Invalid task number. Please try again.\n");
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }
    public static void displayCurrentTaskIndex(){
        int index = 0;
        for (Task task : selectedTaskList.getTaskList()){
            if(!task.getTaskStatus()) {
                System.out.println(index + ". " + task.getTaskName());
            }
            index++;
        }
    }
    public static void removeTaskByIndex(int indexToRemove){
        if (indexToRemove >= 0 && indexToRemove < selectedTaskList.getTaskList().size()){
            Task removeTask = selectedTaskList.getTaskList().remove(indexToRemove);
            System.out.println("Task: " + removeTask.getTaskName() + " Has been removed.\n");
        } else {
            System.out.println("Invalid entry. Please try again.\n");
        }
    }
    public static void completeTaskByIndex(int indexToComplete){
        if ( indexToComplete >= 0 &&  indexToComplete < selectedTaskList.getTaskList().size()){
            Task taskToComplete = selectedTaskList.getTaskList().get(indexToComplete);
            taskToComplete.setTaskComplete();
            taskToComplete.printTaskInfo();
        } else {
            System.out.println("Index out of range. Please enter a valid index and try again.\n");
        }
    }
    public static void endTaskManager(){
        scanner.close();
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
        System.out.println("Please enter the index of the task list you'd like to remove, or type " +
        "'exit' to return to the main menu.\")");
        displayTaskListIndex();
        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("exit")) {
            System.out.println("Selection canceled by user.");
            return;
        }
        try {
            int indexToRemove = Integer.parseInt(input);
            if (indexToRemove > taskListList.size()) {
                System.out.println("Task index is out of range. Please try again. \n");
            } else {
                taskListList.remove(indexToRemove);
                System.out.println("The selected task list has been removed.");
            }
        }
        catch (NumberFormatException nfe){
            System.out.println("Invalid entry. Please enter a valid number.");
        }
    }
    public static boolean selectTaskList(){
        System.out.println("Please enter the index of the task list you'd like to select, or type " +
        "'exit' to return to the main menu.");
        displayTaskListIndex();
        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("exit")) {
            System.out.println("Selection canceled by user.");
            return false;
        }
        try {
            int indexToSelect = Integer.parseInt(input);
            if (indexToSelect >= 0 && indexToSelect < taskListList.size()) {
                selectedTaskList = taskListList.get(indexToSelect);
                System.out.println("You have selected the task list: " + selectedTaskList.getName());
                return false;
            } else {
                System.out.println("Task index is out of range. Please try again. \n");
                return true;
            }
        }
        catch (NumberFormatException nfe){
            System.out.println("Invalid entry. Please enter a valid number.");
        }
        return true;
    }
    public static void selectTaskListToSwitch(){
        System.out.println("Please select the number of which task list you'd like to switch to, or type " +
        "'exit' to return to the main menu.\") ");
        displayTaskListIndex();
        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("exit")) {
            System.out.println("Selection canceled by user.");
            return;
        }
        try {
            int indexToSwitchList = Integer.parseInt(input);
            if (indexToSwitchList >= 0 && indexToSwitchList < taskListList.size()) {
                selectedTaskList = taskListList.get(indexToSwitchList);
                System.out.println("Switched to task list: " + selectedTaskList.getName());
            } else {
                System.out.println("Task index is out of range. Please try again.");
            }
        }
        catch (NumberFormatException nfe){
            System.out.println("Invalid entry. Please enter a valid number.");
        }
    }
    //this method returns true if any task in the task list is complete
    public static boolean hasNoCompletedTasks(TaskList selectedTaskList){
        for (Task task : selectedTaskList.getTaskList()){
            if(task.getTaskStatus()){
                return false;
            }
        }
        return true;
    }

    //this method returns true if any task in the task list is incomplete
    public static boolean hasNoIncompleteTasks(TaskList selectedTaskList){
        for (Task task : selectedTaskList.getTaskList()){
            if(!task.getTaskStatus()){
                return false;
            }
        }
        return true;
    }
}