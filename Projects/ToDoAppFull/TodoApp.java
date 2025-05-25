import java.util.Scanner;
import java.util.InputMismatchException;

public class TodoApp {
    private TodoManager todoManager;
    private Scanner scanner;

    public TodoApp() {
        this.todoManager = new TodoManager();
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        printWelcome();

        while (true) {
            printMainMenu();
            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1 -> addNewTask();
                case 2 -> viewTasksMenu();
                case 3 -> updateTaskMenu();
                case 4 -> markTaskMenu();
                case 5 -> deleteTaskById();
                case 6 -> searchTasksMenu();
                case 7 -> setTaskDueDate();
                case 8 -> todoManager.showStatistics();
                case 9 -> todoManager.clearCompletedTasks();
                case 0 -> {
                    System.out.println("Thank you for using Todo App! Goodbye! 👋");
                    return;
                }
                default -> System.out.println("❌ Invalid choice. Please try again.");
            }

            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
        }
    }

    private void printWelcome() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("               WELCOME TO TODO APP");
        System.out.println("           Your Personal Task Manager");
        System.out.println("=".repeat(60));
    }

    private void printMainMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("                 MAIN MENU");
        System.out.println("=".repeat(50));
        System.out.println("1. ➕ Add New Task");
        System.out.println("2. 👁️  View Tasks");
        System.out.println("3. ✏️  Update Task");
        System.out.println("4. ✅ Mark Task Status");
        System.out.println("5. 🗑️  Delete Task");
        System.out.println("6. 🔍 Search Tasks");
        System.out.println("7. 📅 Set Due Date");
        System.out.println("8. 📊 View Statistics");
        System.out.println("9. 🧹 Clear Completed Tasks");
        System.out.println("0. 🚪 Exit");
        System.out.println("=".repeat(50));
    }

    private void addNewTask() {
        System.out.println("\n--- ADD NEW TASK ---");

        System.out.print("Enter task title: ");
        String title = scanner.nextLine().trim();
        if (title.isEmpty()) {
            System.out.println("❌ Title cannot be empty.");
            return;
        }

        System.out.print("Enter task description: ");
        String description = scanner.nextLine().trim();

        Task.Priority priority = selectPriority();
        if (priority == null)
            return;

        Task.Category category = selectCategory();
        if (category == null)
            return;

        todoManager.addTask(title, description, priority, category);
    }

    private void viewTasksMenu() {
        System.out.println("\n--- VIEW TASKS ---");
        System.out.println("1. All Tasks");
        System.out.println("2. Pending Tasks");
        System.out.println("3. Completed Tasks");
        System.out.println("4. By Priority");
        System.out.println("5. By Category");

        int choice = getIntInput("Select view option: ");

        switch (choice) {
            case 1 -> todoManager.viewAllTasks();
            case 2 -> todoManager.viewTasksByStatus(false);
            case 3 -> todoManager.viewTasksByStatus(true);
            case 4 -> viewByPriority();
            case 5 -> viewByCategory();
            default -> System.out.println("❌ Invalid choice.");
        }
    }

    private void viewByPriority() {
        Task.Priority priority = selectPriority();
        if (priority != null) {
            todoManager.viewTasksByPriority(priority);
        }
    }

    private void viewByCategory() {
        Task.Category category = selectCategory();
        if (category != null) {
            todoManager.viewTasksByCategory(category);
        }
    }

    private void updateTaskMenu() {
        System.out.println("\n--- UPDATE TASK ---");
        todoManager.viewAllTasks();

        int id = getIntInput("Enter task ID to update: ");

        System.out.print("Enter new title: ");
        String title = scanner.nextLine().trim();
        if (title.isEmpty()) {
            System.out.println("❌ Title cannot be empty.");
            return;
        }

        System.out.print("Enter new description: ");
        String description = scanner.nextLine().trim();

        Task.Priority priority = selectPriority();
        if (priority == null)
            return;

        Task.Category category = selectCategory();
        if (category == null)
            return;

        todoManager.updateTask(id, title, description, priority, category);
    }

    private void markTaskMenu() {
        System.out.println("\n--- MARK TASK STATUS ---");
        todoManager.viewAllTasks();

        int id = getIntInput("Enter task ID: ");

        System.out.println("1. Mark as Completed");
        System.out.println("2. Mark as Pending");
        int choice = getIntInput("Choose action: ");

        switch (choice) {
            case 1 -> todoManager.markTaskComplete(id);
            case 2 -> todoManager.markTaskIncomplete(id);
            default -> System.out.println("❌ Invalid choice.");
        }
    }

    private void deleteTaskById() {
        System.out.println("\n--- DELETE TASK ---");
        todoManager.viewAllTasks();

        int id = getIntInput("Enter task ID to delete: ");

        System.out.print("Are you sure you want to delete this task? (y/N): ");
        String confirm = scanner.nextLine().trim().toLowerCase();

        if (confirm.equals("y") || confirm.equals("yes")) {
            todoManager.deleteTask(id);
        } else {
            System.out.println("Delete operation cancelled.");
        }
    }

    private void searchTasksMenu() {
        System.out.println("\n--- SEARCH TASKS ---");
        System.out.print("Enter search keyword: ");
        String keyword = scanner.nextLine().trim();

        if (keyword.isEmpty()) {
            System.out.println("❌ Search keyword cannot be empty.");
            return;
        }

        todoManager.searchTasks(keyword);
    }

    private void setTaskDueDate() {
        System.out.println("\n--- SET DUE DATE ---");
        todoManager.viewAllTasks();

        int id = getIntInput("Enter task ID: ");

        System.out.println("Enter due date (format: yyyy-MM-dd HH:mm)");
        System.out.print("Example: 2024-12-25 23:59 : ");
        String dueDateStr = scanner.nextLine().trim();

        todoManager.setDueDate(id, dueDateStr);
    }

    private Task.Priority selectPriority() {
        System.out.println("\nSelect Priority:");
        Task.Priority[] priorities = Task.Priority.values();
        for (int i = 0; i < priorities.length; i++) {
            System.out.println((i + 1) + ". " + priorities[i].getName());
        }

        int choice = getIntInput("Choose priority: ");
        if (choice >= 1 && choice <= priorities.length) {
            return priorities[choice - 1];
        } else {
            System.out.println("❌ Invalid priority selection.");
            return null;
        }
    }

    private Task.Category selectCategory() {
        System.out.println("\nSelect Category:");
        Task.Category[] categories = Task.Category.values();
        for (int i = 0; i < categories.length; i++) {
            System.out.println((i + 1) + ". " + categories[i].getName());
        }

        int choice = getIntInput("Choose category: ");
        if (choice >= 1 && choice <= categories.length) {
            return categories[choice - 1];
        } else {
            System.out.println("❌ Invalid category selection.");
            return null;
        }
    }

    private int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = scanner.nextInt();
                scanner.nextLine(); // consume newline
                return value;
            } catch (InputMismatchException e) {
                System.out.println("❌ Please enter a valid number.");
                scanner.nextLine(); // clear invalid input
            }
        }
    }

    public static void main(String[] args) {
        new TodoApp().run();
    }
}