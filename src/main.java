import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        System.out.println("Enter command:");
        Scanner sc = new Scanner(System.in);
        boolean active = true;
        while (active) {
            switch (sc.nextLine()) {
                case "exit": {
                    System.out.println("Goodbye");
                    active = false;
                    break;
                }
                case "help": {
                    System.out.println("help - shows this help message");
                    System.out.println("create <task_title> <task_text> <hh.mm> <dd.mm.yyyy>> - creates a new task");
                    System.out.println("delete <name> - deletes a task");
                    System.out.println("search <name> - shows a task, if it exists");
                    System.out.println("list - lists all tasks");
                    System.out.println("edit <name> - edits a task");
                    System.out.println("exit - exits the program");
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
