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
                try {
                    if (parts.length <= 1 || parts[1].trim().isEmpty()) {
                        throw new ElmachoExceptions("HELLO!!!!!!! Your todo is empty.");
                    }
                    String task = parts[1];
                    ToDo todo = new ToDo(task);
                    arrayA[n] = todo;
                    n = n + 1;
                    System.out.println("____________________________________________________________");
                    System.out.println("Added task:\n  " + todo.toString() + "\nNow you have " + n + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } catch (ElmachoExceptions e) {
                    System.out.println("____________________________________________________________");
                    System.out.println(e.getMessage());
                    System.out.println("____________________________________________________________");
                }
            }
            else if (instruction.startsWith("deadline")) {
                String[] parts = instruction.split(" ", 2);
                try {
                    if (parts.length <= 1 || parts[1].trim().isEmpty()) {
                        throw new ElmachoExceptions("HELLO!!!!!!! Your deadline is empty.");
                    }
                    String[] details = parts[1].split("/by");
                    String task = details[0];
                    if (details.length <=1 || details[1].trim().isEmpty()) {
                        throw new ElmachoExceptions("HELLO!!!!!!! When is your task due by??");
                    }
                    Deadline deadline = new Deadline(task, details[1]);
                    arrayA[n] = deadline;
                    n = n + 1;
                    System.out.println("____________________________________________________________");
                    System.out.println("Added task:\n  " + deadline.toString() + "\nNow you have " + n + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } catch (ElmachoExceptions e) {
                    System.out.println("____________________________________________________________");
                    System.out.println(e.getMessage());
                    System.out.println("____________________________________________________________");
                }
            }
            else if (instruction.startsWith("event")) {
                String[] parts = instruction.split(" ", 2);
               try {
                   if (parts.length <= 1 || parts[1].trim().isEmpty()) {
                       throw new ElmachoExceptions("HELLOOOO!!! Your event is empty??!??!");
                   }
                   String[] details = parts[1].split("/from");
                   String task = details[0];
                   if (details.length <=1 || details[1].trim().isEmpty()) {
                       throw new ElmachoExceptions("HELLOOO! When does your event start??");
                   }
                   String[] times = details[1].split("/to");
                   if (times.length <=1 || details[1].trim().isEmpty()) {
                       throw new ElmachoExceptions("HELLOOO! When does your event end??");
                   }
                   Event event = new Event(task, times[0], times[1]);
                   arrayA[n] = event;
                   n = n + 1;
                   System.out.println("____________________________________________________________");
                   System.out.println("Added task:\n  " + event.toString() + "\nNow you have " + n + " tasks in the list.");
                   System.out.println("____________________________________________________________");
               } catch (ElmachoExceptions e) {
                   System.out.println("____________________________________________________________");
                   System.out.println(e.getMessage());
                   System.out.println("____________________________________________________________");
               }
            }
            else if (Objects.equals(instruction, "bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("OkBye.");
                System.out.println("____________________________________________________________");
                break;
            }
            else {
                try {
                    throw new ElmachoExceptions("Speak a language I can understand please.");
                } catch (ElmachoExceptions e) {
                    System.out.println("____________________________________________________________");
                    System.out.println(e.getMessage());
                    System.out.println("____________________________________________________________");
                }
            }
        }
    }
}

