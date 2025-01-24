import java.util.Objects;
import java.util.Scanner;

public class Elmacho {

    private void list(Task[] arrayA, int n) {
        System.out.println("____________________________________________________________\n");
        for (int i = 0; i < n; i++) {
            System.out.println(i + 1 + "." + arrayA[i].toString());
        }
        System.out.println("____________________________________________________________");
    }

    private void markTask(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println("Finally. Marked this task as done:\n  ["
                + task.getStatusIcon() + "] " + task.getDescription());
        System.out.println("____________________________________________________________");
    }

    private void unmarkTask(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println("Another task not done...:\n  ["
                + task.getStatusIcon() + "] " + task.getDescription());
        System.out.println("____________________________________________________________");
    }

    private ToDo makeToDo(String instruction) throws ElmachoExceptions {
        String[] parts = instruction.split(" ", 2);
        if (parts.length <= 1 || parts[1].trim().isEmpty()) {
            throw new ElmachoExceptions("HELLO!!!!!!! Your todo is empty.");
        }
        String task = parts[1];
        ToDo todo = new ToDo(task);
        return todo;
    }

    private Deadline makeDeadline(String instruction) throws ElmachoExceptions {
        String[] parts = instruction.split(" ", 2);
        if (parts.length <= 1) {
            throw new ElmachoExceptions("HELLO!!!!!!! Your deadline is empty.");
        }
        String[] details = parts[1].split("/by");
        String task = details[0];
        String by = details.length > 1 ? details[1].trim() : "";

        Deadline deadline = new Deadline(task, by);
        return deadline;
    }

    private Event makeEvent(String instruction) throws ElmachoExceptions {
        String[] parts = instruction.split(" ", 2);
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
        return event;
    }

    public static void main(String[] args) {
        String name = "Elmacho";
        System.out.println("____________________________________________________________");
        System.out.println("What. I'm " + name);
        System.out.println("What you want?");
        System.out.println("____________________________________________________________");
        Elmacho elmacho = new Elmacho();
        Scanner scanner = new Scanner(System.in);
        int n = 0;
        Task[] arrayA = new Task[100];

        while (true) {
            String instruction = scanner.nextLine();
            if (instruction.equals("list")) {
                elmacho.list(arrayA, n);
            }
            else if (instruction.startsWith("mark") || instruction.startsWith("unmark")) {
                String[] parts = instruction.split(" ");
                if (parts.length > 1) {
                    try {
                        int number = Integer.parseInt(parts[1]) - 1;
                        if (number >= 0 && number < n) {
                            if (instruction.startsWith("mark")) {
                                arrayA[number].mark();
                                elmacho.markTask(arrayA[number]);
                            } else {
                                arrayA[number].unmark();
                                elmacho.unmarkTask(arrayA[number]);
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
                try {
                    ToDo todo = elmacho.makeToDo(instruction);
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
                try {
                    Deadline deadline = elmacho.makeDeadline(instruction);
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
                try {
                    Event event = elmacho.makeEvent(instruction);
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

