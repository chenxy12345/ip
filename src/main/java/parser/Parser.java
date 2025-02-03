package parser;

import command.Command;
import command.AddCommand;
import command.DeleteCommand;
import command.ExitCommand;
import command.ListCommand;
import command.MarkCommand;
import command.StoreCommand;
import command.UnmarkCommand;
import exceptions.ElmachoExceptions;
import task.Deadline;
import task.Event;
import task.ToDo;
import task.Task;


public class Parser {

    public Parser() {
    }

    public static Command parse(String instruction) throws ElmachoExceptions {
        String[] parts = instruction.split(" ", 2);
        String command = parts[0];

        if (command.equals("delete")) {
            try {
                int number = Integer.parseInt(parts[1]);
                return new DeleteCommand(number);
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid task number.");
            }
        }

        if (command.equals("mark")) {
            try {
                int number = Integer.parseInt(parts[1]);
                return new MarkCommand(number);
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid task number.");
            }
        }
        if (command.equals("unmark")) {
            try {
                int number = Integer.parseInt(parts[1]);
                return new UnmarkCommand(number);
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid task number.");
            }
        }
        if (command.equals("T") || command.equals("D") || command.equals("E")) {
            String[] details = parts[1].split("/");
            boolean isDone = details[0].equals("1") ? true : false;
            String storeDescription = details[1];

            if (command.equals("T")) {
                return new StoreCommand(new ToDo(storeDescription, isDone));
            }
            if (command.equals("D")) {
                String[] storeDetails = details[2].split("by");
                String dueDate = storeDetails[1].trim();
                return new StoreCommand(new Deadline(storeDescription, dueDate, isDone));
            }
            if (command.equals("E")) {
                String[] storeDetails = details[2].split("from");
                String[] storeDetails2 = storeDetails[1].split("to");
                String fromDate = storeDetails2[0].trim();
                String toDate = storeDetails2[1].trim();
                return new StoreCommand(new Event(storeDescription, fromDate, toDate, isDone));
            }
        }

        if (command.equals("bye")) {
            return new ExitCommand();
        }

        if (command.equals("list")) {
            return new ListCommand();
        }

        // Adding of tasks
        if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
            if (parts.length <= 1 || parts[1].trim().isEmpty()) {
                throw new ElmachoExceptions("HELLOOOO!!! Your task is empty??!??!");
            } else {
                if (command.equals("todo")) {
                    String task = parts[1].trim();
                    return new AddCommand(new ToDo(task, false));
                }
                if (command.equals("deadline")) {
                    String[] deadlineDetails = parts[1].split("/by ", 2);  // Split on "/by " with a limit of 2
                    if (deadlineDetails.length < 2) {
                        throw new ElmachoExceptions("HELLOOO! When is the deadline??");
                    }
                    String task = deadlineDetails[0].trim();
                    String dueDate = deadlineDetails[1].trim();  // This will be "2019-01-01 1800"
                    return new AddCommand(new Deadline(task, dueDate, false));
                }
                if (command.equals("event")) {
                    String[] eventDetails = parts[1].split("/from ", 2);
                    if (eventDetails.length < 2) {
                        throw new ElmachoExceptions("HELLOOO! When does your event start??");
                    }
                    String task = eventDetails[0].trim();

                    String[] timeDetails = eventDetails[1].split("/to ", 2);
                    if (timeDetails.length < 2) {
                        throw new ElmachoExceptions("HELLO. When does your event end?");
                    }

                    String from = timeDetails[0].trim();
                    String to = timeDetails[1].trim();

                    return new AddCommand(new Event(task, from, to, false));
                }
            }
        }
        return new Command();
    }
}
