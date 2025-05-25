import java.util.*;
import java.util.stream.Collectors;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TodoManager {
    private List<Task> tasks;
    private FileManager fileManager;

    public TodoManager() {
        this.tasks = new ArrayList<>();
        this.fileManager = new FileManager();
        loadTasks();
    }

    public void addTask(String title, String description, Task.Priority priority, Task.Category category) {
        Task task = new Task(title, description, priority, category);
        tasks.add(task);
        saveTasks();
        System.out.println("✓ Task added successfully with ID: " + task.getId());
    }

    public void viewAllTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found. Add some tasks to get started!");
            return;
        }

        System.out.println("\n" + "=".repeat(80));
        System.out.println("                           ALL TASKS");
        System.out.println("=".repeat(80));

        for (Task task : tasks) {
            System.out.println(task);
            System.out.println("-".repeat(80));
        }
    }

    public void viewTasksByStatus(boolean completed) {
        List<Task> filteredTasks = tasks.stream()
                .filter(task -> task.isCompleted() == completed)
                .collect(Collectors.toList());

        String status = completed ? "COMPLETED" : "PENDING";
        System.out.println("\n" + "=".repeat(80));
        System.out.println("                        " + status + " TASKS");
        System.out.println("=".repeat(80));

        if (filteredTasks.isEmpty()) {
            System.out.println("No " + status.toLowerCase() + " tasks found.");
            return;
        }

        for (Task task : filteredTasks) {
            System.out.println(task);
            System.out.println("-".repeat(80));
        }
    }

    public void viewTasksByPriority(Task.Priority priority) {
        List<Task> filteredTasks = tasks.stream()
                .filter(task -> task.getPriority() == priority)
                .collect(Collectors.toList());

        System.out.println("\n" + "=".repeat(80));
        System.out.println("                    " + priority.getName().toUpperCase() + " PRIORITY TASKS");
        System.out.println("=".repeat(80));

        if (filteredTasks.isEmpty()) {
            System.out.println("No " + priority.getName().toLowerCase() + " priority tasks found.");
            return;
        }

        for (Task task : filteredTasks) {
            System.out.println(task);
            System.out.println("-".repeat(80));
        }
    }

    public void viewTasksByCategory(Task.Category category) {
        List<Task> filteredTasks = tasks.stream()
                .filter(task -> task.getCategory() == category)
                .collect(Collectors.toList());

        System.out.println("\n" + "=".repeat(80));
        System.out.println("                    " + category.getName().toUpperCase() + " TASKS");
        System.out.println("=".repeat(80));

        if (filteredTasks.isEmpty()) {
            System.out.println("No " + category.getName().toLowerCase() + " tasks found.");
            return;
        }

        for (Task task : filteredTasks) {
            System.out.println(task);
            System.out.println("-".repeat(80));
        }
    }

    public void markTaskComplete(int id) {
        Task task = findTaskById(id);
        if (task != null) {
            task.setCompleted(true);
            saveTasks();
            System.out.println("✓ Task '" + task.getTitle() + "' marked as completed!");
        } else {
            System.out.println("❌ Task with ID " + id + " not found.");
        }
    }

    public void markTaskIncomplete(int id) {
        Task task = findTaskById(id);
        if (task != null) {
            task.setCompleted(false);
            saveTasks();
            System.out.println("✓ Task '" + task.getTitle() + "' marked as pending!");
        } else {
            System.out.println("❌ Task with ID " + id + " not found.");
        }
    }

    public void updateTask(int id, String newTitle, String newDescription,
            Task.Priority newPriority, Task.Category newCategory) {
        Task task = findTaskById(id);
        if (task != null) {
            task.setTitle(newTitle);
            task.setDescription(newDescription);
            task.setPriority(newPriority);
            task.setCategory(newCategory);
            saveTasks();
            System.out.println("✓ Task updated successfully!");
        } else {
            System.out.println("❌ Task with ID " + id + " not found.");
        }
    }

    public void setDueDate(int id, String dueDateStr) {
        Task task = findTaskById(id);
        if (task == null) {
            System.out.println("❌ Task with ID " + id + " not found.");
            return;
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dueDate = LocalDateTime.parse(dueDateStr, formatter);
            task.setDueDate(dueDate);
            saveTasks();
            System.out.println("✓ Due date set successfully!");
        } catch (DateTimeParseException e) {
            System.out.println("❌ Invalid date format. Use: yyyy-MM-dd HH:mm");
        }
    }

    public void deleteTask(int id) {
        Task task = findTaskById(id);
        if (task != null) {
            tasks.remove(task);
            saveTasks();
            System.out.println("✓ Task '" + task.getTitle() + "' deleted successfully!");
        } else {
            System.out.println("❌ Task with ID " + id + " not found.");
        }
    }

    public void searchTasks(String keyword) {
        List<Task> matchingTasks = tasks.stream()
                .filter(task -> task.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                        task.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());

        System.out.println("\n" + "=".repeat(80));
        System.out.println("                    SEARCH RESULTS FOR: " + keyword);
        System.out.println("=".repeat(80));

        if (matchingTasks.isEmpty()) {
            System.out.println("No tasks found matching '" + keyword + "'");
            return;
        }

        for (Task task : matchingTasks) {
            System.out.println(task);
            System.out.println("-".repeat(80));
        }
    }

    public void showStatistics() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available for statistics.");
            return;
        }

        long totalTasks = tasks.size();
        long completedTasks = tasks.stream().filter(Task::isCompleted).count();
        long pendingTasks = totalTasks - completedTasks;

        Map<Task.Priority, Long> priorityCount = tasks.stream()
                .collect(Collectors.groupingBy(Task::getPriority, Collectors.counting()));

        Map<Task.Category, Long> categoryCount = tasks.stream()
                .collect(Collectors.groupingBy(Task::getCategory, Collectors.counting()));

        System.out.println("\n" + "=".repeat(60));
        System.out.println("                    TASK STATISTICS");
        System.out.println("=".repeat(60));
        System.out.println("Total Tasks: " + totalTasks);
        System.out.println("Completed: " + completedTasks + " (" +
                String.format("%.1f", (completedTasks * 100.0 / totalTasks)) + "%)");
        System.out.println("Pending: " + pendingTasks + " (" +
                String.format("%.1f", (pendingTasks * 100.0 / totalTasks)) + "%)");

        System.out.println("\nBy Priority:");
        for (Task.Priority priority : Task.Priority.values()) {
            long count = priorityCount.getOrDefault(priority, 0L);
            System.out.println("  " + priority.getName() + ": " + count);
        }

        System.out.println("\nBy Category:");
        for (Task.Category category : Task.Category.values()) {
            long count = categoryCount.getOrDefault(category, 0L);
            System.out.println("  " + category.getName() + ": " + count);
        }
        System.out.println("=".repeat(60));
    }

    public void clearCompletedTasks() {
        int sizeBefore = tasks.size();
        tasks.removeIf(Task::isCompleted);
        int sizeAfter = tasks.size();
        int removedCount = sizeBefore - sizeAfter;

        if (removedCount > 0) {
            saveTasks();
            System.out.println("✓ Removed " + removedCount + " completed task(s).");
        } else {
            System.out.println("No completed tasks to remove.");
        }
    }

    private Task findTaskById(int id) {
        return tasks.stream()
                .filter(task -> task.getId() == id)
                .findFirst()
                .orElse(null);
    }

    private void saveTasks() {
        fileManager.saveTasks(tasks);
    }

    private void loadTasks() {
        tasks = fileManager.loadTasks();
        if (!tasks.isEmpty()) {
            int maxId = tasks.stream().mapToInt(Task::getId).max().orElse(0);
            Task.setIdCounter(maxId + 1);
        }
    }

    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }
}