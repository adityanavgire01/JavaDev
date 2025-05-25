import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.Serializable;

public class Task implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int idCounter = 1;

    private int id;
    private String title;
    private String description;
    private Priority priority;
    private boolean completed;
    private LocalDateTime createdAt;
    private LocalDateTime dueDate;
    private Category category;

    public enum Priority {
        LOW(1, "Low"),
        MEDIUM(2, "Medium"),
        HIGH(3, "High"),
        URGENT(4, "Urgent");

        private final int level;
        private final String name;

        Priority(int level, String name) {
            this.level = level;
            this.name = name;
        }

        public int getLevel() {
            return level;
        }

        public String getName() {
            return name;
        }
    }

    public enum Category {
        WORK("Work"),
        PERSONAL("Personal"),
        SHOPPING("Shopping"),
        HEALTH("Health"),
        EDUCATION("Education"),
        OTHER("Other");

        private final String name;

        Category(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public Task(String title, String description, Priority priority, Category category) {
        this.id = idCounter++;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.category = category;
        this.completed = false;
        this.createdAt = LocalDateTime.now();
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public static void setIdCounter(int counter) {
        idCounter = counter;
    }

    public static int getIdCounter() {
        return idCounter;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        StringBuilder sb = new StringBuilder();

        sb.append("ID: ").append(id)
                .append(" | Title: ").append(title)
                .append(" | Status: ").append(completed ? "✓ DONE" : "○ PENDING")
                .append(" | Priority: ").append(priority.getName())
                .append(" | Category: ").append(category.getName())
                .append("\nDescription: ").append(description)
                .append("\nCreated: ").append(createdAt.format(formatter));

        if (dueDate != null) {
            sb.append(" | Due: ").append(dueDate.format(formatter));
        }

        return sb.toString();
    }
}