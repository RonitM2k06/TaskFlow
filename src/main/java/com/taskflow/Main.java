package com.taskflow;

import com.taskflow.model.Task;
import com.taskflow.service.TaskService;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Simple interactive CLI for managing tasks.
 */
public class Main {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final TaskService SERVICE = new TaskService();

    public static void main(String[] args) {
        System.out.println("Welcome to TaskFlow – your lightweight CLI task manager");
        boolean running = true;
        while (running) {
            printMenu();
            String choice = SCANNER.nextLine().trim();
            switch (choice) {
                case "1" -> listTasks();
                case "2" -> addTask();
                case "3" -> completeTask();
                case "4" -> deleteTask();
                case "5" -> filterByTag();
                case "0" -> running = false;
                default -> System.out.println("Invalid selection, try again.");
            }
        }
        System.out.println("Goodbye!");
    }

    private static void printMenu() {
        System.out.println("\n--- Menu ---");
        System.out.println("1) List all tasks");
        System.out.println("2) Add a new task");
        System.out.println("3) Mark task as complete");
        System.out.println("4) Delete a task");
        System.out.println("5) Filter tasks by tag");
        System.out.println("0) Exit");
        System.out.print("Select an option: ");
    }

    private static void listTasks() {
        var tasks = SERVICE.list();
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
            return;
        }
        System.out.println("\nCurrent Tasks:");
        for (Task t : tasks) {
            System.out.printf("ID: %s | Title: %s | Completed: %s | Due: %s | Tags: %s\n",
                    t.getId(), t.getTitle(), t.isCompleted(),
                    t.getDueDate() != null ? t.getDueDate() : "-",
                    String.join(", ", t.getTags()));
        }
    }

    private static void addTask() {
        System.out.print("Title: ");
        String title = SCANNER.nextLine().trim();
        System.out.print("Description (optional): ");
        String desc = SCANNER.nextLine().trim();
        System.out.print("Tags (comma separated, optional): ");
        String tagLine = SCANNER.nextLine().trim();
        String[] tags = tagLine.isEmpty() ? new String[0] : tagLine.split("\\s*,\\s*");
        System.out.print("Due date (YYYY-MM-DD, optional): ");
        String dateStr = SCANNER.nextLine().trim();
        LocalDate due = null;
        if (!dateStr.isEmpty()) {
            try {
                due = LocalDate.parse(dateStr);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format – ignoring due date.");
            }
        }
        Task created = SERVICE.add(title, desc.isEmpty() ? null : desc, tags, due);
        System.out.println("Task added with ID: " + created.getId());
    }

    private static void completeTask() {
        System.out.print("Enter task ID to complete: ");
        String id = SCANNER.nextLine().trim();
        if (SERVICE.complete(id)) {
            System.out.println("Task marked as completed.");
        } else {
            System.out.println("Task not found.");
        }
    }

    private static void deleteTask() {
        System.out.print("Enter task ID to delete: ");
        String id = SCANNER.nextLine().trim();
        if (SERVICE.delete(id)) {
            System.out.println("Task deleted.");
        } else {
            System.out.println("Task not found.");
        }
    }

    private static void filterByTag() {
        System.out.print("Enter tag to filter by: ");
        String tag = SCANNER.nextLine().trim();
        var filtered = SERVICE.filterByTag(tag);
        if (filtered.isEmpty()) {
            System.out.println("No tasks with that tag.");
            return;
        }
        System.out.println("\nTasks with tag '" + tag + "':");
        for (Task t : filtered) {
            System.out.printf("ID: %s | Title: %s | Completed: %s | Due: %s\n",
                    t.getId(), t.getTitle(), t.isCompleted(),
                    t.getDueDate() != null ? t.getDueDate() : "-");
        }
    }
}
