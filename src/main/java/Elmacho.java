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
                System.out.println("____________________________________________________________\n Your Tasks:");
                for (int i = 0; i < n; i++) {
                    System.out.println(i + 1 + ". [" + arrayA[i].getStatusIcon() + "] " + arrayA[i].getDescription());
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

