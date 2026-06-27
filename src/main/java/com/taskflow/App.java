package com.taskflow;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("====================================");
        System.out.println("    Welcome to TaskFlow CLI v1.0    ");
        System.out.println("====================================");

        while (running) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. View Tasks & Metrics");
            System.out.println("2. Add a New Task");
            System.out.println("3. Toggle Task Completion");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> displayTasksAndMetrics(manager);
                case "2" -> {
                    System.out.print("Enter task description: ");
                    String desc = scanner.nextLine();
                    if (!desc.isBlank()) {
                        manager.addTask(desc);
                        System.out.println("Task added successfully!");
                    } else {
                        System.out.println("Task description cannot be empty.");
                    }
                }
                case "3" -> {
                    System.out.print("Enter Task ID to toggle: ");
                    try {
                        int id = Integer.parseInt(scanner.nextLine());
                        boolean success = manager.toggleTaskCompletion(id);
                        if (success) {
                            System.out.println("Task status updated.");
                        } else {
                            System.out.println("Task ID not found.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid numerical ID.");
                    }
                }
                case "4" -> {
                    running = false;
                    System.out.println("Exiting TaskFlow. Keep grinding!");
                }
                default -> System.out.println("Invalid selection. Try again.");
            }
        }
        scanner.close();
    }

    private static void displayTasksAndMetrics(TaskManager manager) {
        System.out.println("\n================ CURRENT TASKS ================");
        if (manager.getAllTasks().isEmpty()) {
            System.out.println("No tasks tracked yet.");
        } else {
            for (Task task : manager.getAllTasks()) {
                String statusBox = task.isCompleted() ? "[X]" : "[ ]";
                System.out.printf("%s ID: %d | %s\n", statusBox, task.id(), task.description());
            }
        }
        System.out.println("-----------------------------------------------");
        System.out.printf("Current Completion Efficiency: %.2f%%\n", manager.calculateCompletionRate());
        System.out.println("===============================================");
    }
}