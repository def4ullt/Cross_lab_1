import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Enter command:");
        Scanner sc = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();
        boolean active = true;

        while (active) {
            String line = sc.nextLine().trim();
            String[] parts = line.split(" ");
            String command = parts[0];

            switch (command) {
                case "exit": {
                    System.out.println("Goodbye");
                    active = false;
                    break;
                }
                case "help": {
                    System.out.println("help - shows this help message");
                    System.out.println("create <task_title> <task_text> <hh.mm> <dd.MM.yyyy> - creates a new task");
                    System.out.println("delete <name> - deletes a task");
                    System.out.println("search <name> - shows a task, if it exists");
                    System.out.println("list - lists all tasks");
                    System.out.println("sort_name — show all tasks sorted by name");
                    System.out.println("sort_time — show all tasks sorted by date");
                    System.out.println("edit <name> - edits a task");
                    System.out.println("exit - exits the program");
                    break;
                }
                case "create": {
                    if (parts.length >= 5) {
                        try {
                            String taskTitle = parts[1];
                            String taskText = parts[2];
                            String time = parts[3];
                            String dateYear = parts[4];
                            String[] dateParts = dateYear.split("\\.");

                            if (dateParts.length == 3) {
                                String date = dateParts[0] + "." + dateParts[1];
                                String year = dateParts[2];
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH.mm dd.MM.yyyy");
                                LocalDateTime endTime = LocalDateTime.parse(time + " " + date + "." + year, formatter);
                                taskManager.createTask(taskTitle, taskText, endTime);
                                System.out.println("Task created: " + taskManager.getTask(taskManager.getAllTasks().size()));
                            } else {
                                System.out.println("Invalid date format. Use dd.MM.yyyy");
                            }

                        } catch (DateTimeParseException e) {
                            System.out.println("Invalid date/time format. Use HH.mm dd.MM.yyyy");
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("Invalid create command. Use create <task_title> <task_text> <hh.mm> <dd.MM.yyyy>");
                        }
                    } else {
                        System.out.println("Invalid create command. Use create <task_title> <task_text> <hh.mm> <dd.MM.yyyy>");
                    }
                    break;
                }
                case "delete": {
                    if (parts.length > 1) {
                        String taskName = parts[1];
                        Task taskToDelete = taskManager.getAllTasks().stream()
                                .filter(task -> task.getName().equals(taskName))
                                .findFirst()
                                .orElse(null);

                        if (taskToDelete != null) {
                            taskManager.deleteTask(taskToDelete.getId());
                            System.out.println("Task deleted: " + taskToDelete);
                        } else {
                            System.out.println("Task not found.");
                        }
                    } else {
                        System.out.println("Invalid delete command. Use delete <task_name>");
                    }
                    break;
                }
                case "search": {
                    if (parts.length > 1) {
                        String taskName = parts[1];
                        Task foundTask = taskManager.getAllTasks().stream()
                                .filter(task -> task.getName().equals(taskName))
                                .findFirst()
                                .orElse(null);

                        if (foundTask != null) {
                            System.out.println(foundTask);
                        } else {
                            System.out.println("Task not found.");
                        }
                    } else {
                        System.out.println("Invalid search command. Use search <task_name>");
                    }
                    break;
                }
                case "sort_name":
                    taskManager.displayTasks(taskManager.sortByName());
                    break;
                case "sort_time":
                    taskManager.displayTasks(taskManager.sortByEndTime());
                    break;
                case "list":
                    taskManager.displayTasks(taskManager.getAllTasks());
                    break;
                case "edit": {
                    if (parts.length > 1) {
                        String taskName = parts[1];
                        Task taskToEdit = taskManager.getAllTasks().stream()
                                .filter(task -> task.getName().equals(taskName))
                                .findFirst()
                                .orElse(null);

                        if (taskToEdit != null) {
                            System.out.println("Enter new name (or press Enter to skip):");
                            String newName = sc.nextLine().trim();
                            if (!newName.isEmpty()) {
                                taskManager.updateTaskName(taskToEdit.getId(), newName);
                            }

                            System.out.println("Enter new text (or press Enter to skip):");
                            String newText = sc.nextLine().trim();
                            if (!newText.isEmpty()) {
                                taskManager.updateTaskText(taskToEdit.getId(), newText);
                            }

                            System.out.println("Enter new end time (HH.mm dd.MM.yyyy, or press Enter to skip):");
                            String newEndTimeStr = sc.nextLine().trim();
                            if (!newEndTimeStr.isEmpty()) {
                                try {
                                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH.mm dd.MM.yyyy");
                                    LocalDateTime newEndTime = LocalDateTime.parse(newEndTimeStr, formatter);
                                    taskManager.updateTaskEndTime(taskToEdit.getId(), newEndTime);
                                } catch (DateTimeParseException e) {
                                    System.out.println("Invalid date/time format.");
                                }
                            }
                            System.out.println("Task updated: " + taskManager.readTask(taskToEdit.getId()));
                        } else {
                            System.out.println("Task not found.");
                        }
                    } else {
                        System.out.println("Invalid edit command. Use edit <task_name>");
                    }
                    break;
                }
                default: {
                    System.out.println("Invalid command");
                    break;
                }
            }
        }
    }
}