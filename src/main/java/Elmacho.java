import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class Elmacho {

    private Task[] arrayA;
    private int numberOfTasks;

    public Elmacho() {
        this.arrayA = new Task[100];
        this.numberOfTasks = 0;
    }

    private void list(Task[] arrayA, int n) {
        System.out.println("____________________________________________________________");
        for (int i = 0; i < n; i++) {
            System.out.println(i + 1 + "." + arrayA[i].toString());
        }
        if (n == 0) {
            System.out.println("List is empty");
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
        String task = details[0].trim();
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
        String task = details[0].trim();
        if (details.length <= 1 || details[1].trim().isEmpty()) {
            throw new ElmachoExceptions("HELLOOO! When does your event start??");
        }
        String[] times = details[1].split("/to");
        if (times.length <=1 || details[1].trim().isEmpty()) {
            throw new ElmachoExceptions("HELLOOO! When does your event end??");
        }
        Event event = new Event(task, times[0], times[1]);
        return event;
    }

    private void updateList(int n) {
        try (FileWriter writer = new FileWriter("ElMacho.txt", false)) {
            for (int i = 0; i < n; i++) {
                writer.write(arrayA[i].getInfo() + "\n");
            }
        }
        catch (IOException e) {
            System.out.println("Unable to update list");
        }
    }

    private void restart(String filename) {
        File file = new File(filename);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Unable to create new file");
                return;
            }
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String Line = scanner.nextLine();
                String[] parts = Line.split("\\|");
                boolean isDone = false;
                Task toAdd = null;
                if (parts[1].equals("1")) {
                    isDone = true;
                }
                // To do:
                if (parts[0].equals("T")) {
                    toAdd = makeToDo("todo " + parts[2]);
                }
                // Deadline:
                if (parts[0].equals("D")) {
                    toAdd = makeDeadline("deadline " + parts[2]);
                }
                // Event:
                if (parts[0].equals("E")) {
                    toAdd = makeEvent("event " + parts[2]);
                }
                if (isDone) {
                    toAdd.mark();
                }
                arrayA[numberOfTasks] = toAdd;
                numberOfTasks = numberOfTasks + 1;  // Update the task count
            }
        } catch (ElmachoExceptions e) {
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find file");
        }
    }

    public static void main(String[] args) {
        String name = "Elmacho";
        System.out.println("____________________________________________________________");
        System.out.println("What. I'm " + name);
        System.out.println("What you want?");
        System.out.println("____________________________________________________________");
        Elmacho elmacho = new Elmacho();
        elmacho.restart("ElMacho.txt");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String instruction = scanner.nextLine();
            if (instruction.equals("list")) {
                elmacho.list(elmacho.arrayA, elmacho.numberOfTasks);
            }
            else if (instruction.startsWith("mark") || instruction.startsWith("unmark")) {
                String[] parts = instruction.split(" ");
                if (parts.length > 1) {
                    try {
                        int number = Integer.parseInt(parts[1]) - 1;
                        if (number >= 0 && number < elmacho.numberOfTasks) {
                            if (instruction.startsWith("mark")) {
                                elmacho.arrayA[number].mark();
                                elmacho.markTask(elmacho.arrayA[number]);
                                elmacho.updateList(elmacho.numberOfTasks);
                            } else {
                                elmacho.arrayA[number].unmark();
                                elmacho.unmarkTask(elmacho.arrayA[number]);
                                elmacho.updateList(elmacho.numberOfTasks);
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
            else if (instruction.startsWith("delete")) {
                String[] parts = instruction.split(" ");
                if (parts.length > 1) {
                    try {
                        int number = Integer.parseInt(parts[1]) - 1;
                        Task task = elmacho.arrayA[number];
                        if (number >= 0 && number < elmacho.numberOfTasks) {
                            for (int i = number; i < elmacho.numberOfTasks + 1; i++) {
                                elmacho.arrayA[i] = elmacho.arrayA[i + 1];
                            }
                            elmacho.numberOfTasks = elmacho.numberOfTasks - 1;
                            System.out.println("____________________________________________________________");
                            System.out.println("Deleted this task: \n  "
                                    + task.toString() + "\nNow you have " + elmacho.numberOfTasks + " tasks in the list.");
                            System.out.println("____________________________________________________________");
                            elmacho.updateList(elmacho.numberOfTasks);
                        } else {
                            System.out.println("Invalid task number.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid task number.");
                    }
                } else {
                    System.out.println("Please specify a task number to delete.");
                }
            }
            else if (instruction.startsWith("todo")) {
                try {
                    ToDo todo = elmacho.makeToDo(instruction);
                    elmacho.arrayA[elmacho.numberOfTasks] = todo;
                    elmacho.numberOfTasks = elmacho.numberOfTasks + 1;
                    System.out.println("____________________________________________________________");
                    System.out.println("Added task:\n  " + todo + "\nNow you have " + elmacho.numberOfTasks + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                    elmacho.updateList(elmacho.numberOfTasks);
                } catch (ElmachoExceptions e) {
                    System.out.println("____________________________________________________________");
                    System.out.println(e.getMessage());
                    System.out.println("____________________________________________________________");
                }
            }
            else if (instruction.startsWith("deadline")) {
                try {
                    Deadline deadline = elmacho.makeDeadline(instruction);
                    elmacho.arrayA[elmacho.numberOfTasks] = deadline;
                    elmacho.numberOfTasks = elmacho.numberOfTasks + 1;
                    System.out.println("____________________________________________________________");
                    System.out.println("Added task:\n  " + deadline + "\nNow you have " + elmacho.numberOfTasks + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                    elmacho.updateList(elmacho.numberOfTasks);
                } catch (ElmachoExceptions e) {
                    System.out.println("____________________________________________________________");
                    System.out.println(e.getMessage());
                    System.out.println("____________________________________________________________");
                }
            }
            else if (instruction.startsWith("event")) {
                try {
                    Event event = elmacho.makeEvent(instruction);
                    elmacho.arrayA[elmacho.numberOfTasks] = event;
                    elmacho.numberOfTasks = elmacho.numberOfTasks + 1;
                    System.out.println("____________________________________________________________");
                    System.out.println("Added task:\n  " + event + "\nNow you have " + elmacho.numberOfTasks + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                    elmacho.updateList(elmacho.numberOfTasks);
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

