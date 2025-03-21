import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TaskManager {
    private List<Task> tasks = new ArrayList<>();
    private int nextId = 1;

    public void createTask(String name, String text, LocalDateTime endTime) {
        Task task = new Task(nextId++, name, text, endTime);
        tasks.add(task);
    }

    public void deleteTask(int id) {
        tasks.removeIf(task -> task.getId() == id);
    }

    public Task getTask(int id) {
        return tasks.stream()
                .filter(task -> task.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    // Методи сортування
    public List<Task> sortByName() {
        return tasks.stream()
                .sorted(Comparator.comparing(Task::getName))
                .collect(Collectors.toList());
    }

    public List<Task> sortByEndTime() {
        return tasks.stream()
                .sorted(Comparator.comparing(Task::getEndTime))
                .collect(Collectors.toList());
    }

    // Метод відображення
    public void displayTasks(List<Task> tasks) {
        tasks.forEach(System.out::println);
    }

    // Методи редагування
    public void updateTaskName(int id, String newName) {
        Task task = getTask(id);
        if (task != null) {
            task = new Task(task.getId(), newName, task.getText(), task.getEndTime());
            tasks.set(tasks.indexOf(getTask(id)), task);
        }
    }

    public void updateTaskText(int id, String newText) {
        Task task = getTask(id);
        if (task != null) {
            task = new Task(task.getId(), task.getName(), newText, task.getEndTime());
            tasks.set(tasks.indexOf(getTask(id)), task);
        }
    }

    public void updateTaskEndTime(int id, LocalDateTime newEndTime) {
        Task task = getTask(id);
        if (task != null) {
            task = new Task(task.getId(), task.getName(), task.getText(), newEndTime);
            tasks.set(tasks.indexOf(getTask(id)), task);
        }
    }

    public List<Task> searchTasks(String query) {
        return tasks.stream()
                .filter(task -> task.getName().toLowerCase().contains(query.toLowerCase()) ||
                        task.getText().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }

    public Task readTask(int id) {
        return getTask(id);
    }
}