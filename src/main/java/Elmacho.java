import java.util.Objects;
import java.util.Scanner;

public class Elmacho {
    public static void main(String[] args) {
        String name = "Elmacho";
        System.out.println("____________________________________________________________");
        System.out.println("What. I'm " + name);
        System.out.println("What you want?");
        System.out.println("____________________________________________________________");
        Scanner scanner = new Scanner(System.in);
        int n = 0;
        Task[] arrayA = new Task[100];
        while (true) {
            String instruction = scanner.nextLine();
            if (Objects.equals(instruction, "list")) {
                System.out.println("____________________________________________________________\n");
                for (int i = 0; i < n; i++) {
                    System.out.println(i + 1 + "." + arrayA[i].toString());
                }
                System.out.println("____________________________________________________________");
            }
            else if (instruction.startsWith("mark") || instruction.startsWith("unmark")) {
                String[] parts = instruction.split(" ");
                if (parts.length > 1) {
                    try {
                        int number = Integer.parseInt(parts[1]) - 1;
                        if (number >= 0 && number < n) {
                            if (instruction.startsWith("mark")) {
                                arrayA[number].mark();
                                System.out.println("____________________________________________________________");
                                System.out.println("Finally. Marked this task as done:\n  ["
                                        + arrayA[number].getStatusIcon() + "] " + arrayA[number].getDescription());
                                System.out.println("____________________________________________________________");
                            } else {
                                arrayA[number].unmark();
                                System.out.println("____________________________________________________________");
                                System.out.println("Another task not done...:\n  ["
                                        + arrayA[number].getStatusIcon() + "] " + arrayA[number].getDescription());
                                System.out.println("____________________________________________________________");
                            }
                        } else {
                            System.out.println("Invalid task number.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid task number.");
                    }
                } else {
                    System.out.println("Please specify a task number to mark.");
                }
            }
            else if (instruction.startsWith("todo")) {
                String[] parts = instruction.split(" ", 2);
                String task = parts[1];
                ToDo todo = new ToDo(task);
                arrayA[n] = todo;
                n = n + 1;
                System.out.println("____________________________________________________________");
                System.out.println("Added task:\n  " + todo.toString() + "\nNow you have " + n + " tasks in the list.");
                System.out.println("____________________________________________________________");
            }
            else if (instruction.startsWith("deadline")) {
                String[] parts = instruction.split(" ", 2);
                String[] details = parts[1].split("/by");
                String task = details[0];
                Deadline deadline = new Deadline(task, details[1]);
                arrayA[n] = deadline;
                n = n + 1;
                System.out.println("____________________________________________________________");
                System.out.println("Added task:\n  " + deadline.toString() + "\nNow you have " + n + " tasks in the list.");
                System.out.println("____________________________________________________________");
            }
            else if (instruction.startsWith("event")) {
                String[] parts = instruction.split(" ", 2);
                String[] details = parts[1].split("/from");
                String task = details[0];
                String[] times = details[1].split("/to");
                Event event = new Event(task, times[0], times[1]);
                arrayA[n] = event;
                n = n + 1;
                System.out.println("____________________________________________________________");
                System.out.println("Added task:\n  " + event.toString() + "\nNow you have " + n + " tasks in the list.");
                System.out.println("____________________________________________________________");
            }
            else if (Objects.equals(instruction, "bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("OkBye.");
                System.out.println("____________________________________________________________");
                break;
            }
            else {
                Task task = new Task(instruction);
                arrayA[n] = task;
                n = n + 1;
                System.out.println("____________________________________________________________");
                System.out.println("added: " + instruction);
                System.out.println("____________________________________________________________");
            }
        }
    }
}

