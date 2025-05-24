import java.util.ArrayList;
import java.util.Scanner;

class Task {
    private String description;
    private boolean isCompleted;

    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void markCompleted() {
        this.isCompleted = true;
    }

    @Override
    public String toString() {
        return (isCompleted ? "[X] " : "[ ] ") + description;
    }
}

public class ToDoApp {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            printMenu();
            choice = getIntInput("Enter your choice: ");
            switch (choice) {
                case 1:
                    addTask();
                    break;
                case 2:
                    listTasks();
                    break;
                case 3:
                    markTaskCompleted();
                    break;
                case 4:
                    deleteTask();
                    break;
                case 5:
                    System.out.println("Exiting. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

    private static void printMenu() {
        System.out.println("\n==== To-Do List Menu ====");
        System.out.println("1. Add Task");
        System.out.println("2. List Tasks");
        System.out.println("3. Mark Task as Completed");
        System.out.println("4. Delete Task");
        System.out.println("5. Exit");
    }

    private static void addTask() {
        System.out.print("Enter task description: ");
        String desc = scanner.nextLine();
        tasks.add(new Task(desc));
        System.out.println("Task added!");
    }

    private static void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
            return;
        }
        System.out.println("\nYour Tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    private static void markTaskCompleted() {
        listTasks();
        if (tasks.isEmpty()) return;
        int index = getIntInput("Enter task number to mark as completed: ") - 1;
        if (isValidIndex(index)) {
            tasks.get(index).markCompleted();
            System.out.println("Task marked as completed.");
        } else {
            System.out.println("Invalid task number.");
        }
    }

    private static void deleteTask() {
        listTasks();
        if (tasks.isEmpty()) return;
        int index = getIntInput("Enter task number to delete: ") - 1;
        if (isValidIndex(index)) {
            tasks.remove(index);
            System.out.println("Task deleted.");
        } else {
            System.out.println("Invalid task number.");
        }
    }

    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            scanner.next();
        }
        int input = scanner.nextInt();
        scanner.nextLine(); // consume newline
        return input;
    }

    private static boolean isValidIndex(int index) {
        return index >= 0 && index < tasks.size();
    }
}
