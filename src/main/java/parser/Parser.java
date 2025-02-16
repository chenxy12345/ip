package parser;

import task.Deadline;
import task.Event;
import task.ToDo;

import command.Command;
import command.AddCommand;
import command.DeleteCommand;
import command.ExitCommand;
import command.FindCommand;
import command.ListCommand;
import command.MarkCommand;
import command.StoreCommand;
import command.UnmarkCommand;

import exceptions.ElmachoException;

/**
 * This class is responsible for interpreting user input and converting it into executable commands.
 *
 * <p>This class acts as an intermediary between the UI and the command execution logic,
 *    ensuring that inputs are correctly formatted and valid before being processed.</p>
 */
public class Parser {

    public Parser() {
    }

    /**
     * Returns a Command to be executed afer parsing user input.
     * @param instruction The raw user input as a String
     * @return A Command object that represents the action to be performed.
     * @throws ElmachoException if the user input is incomplete or missing required details.
     */
    public static Command parse(String instruction) throws ElmachoException {
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
        if (command.equals("find")) {
            String keyword = parts[1].trim();
            return new FindCommand(keyword);
        }

        // Loading of tasks from storage
        if (command.equals("T") || command.equals("D") || command.equals("E")) {
            return reloadTask(command, parts);
        }

        if (command.equals("bye")) {
            return new ExitCommand();
        }

        if (command.equals("list")) {
            return new ListCommand();
        }

        // Adding of tasks
        if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
            if (parts.length == 1 || parts[1].trim().isEmpty()) {
                throw new ElmachoException("HELLOOOO!!! Your task is empty??!??!");
            } else {
                return makeNewTask(command, parts);
            }
        }
        return new Command();
    }

    public static Command makeNewTask(String command, String[] description) throws ElmachoException {
        switch (command) {
            case "todo" -> {
                String task = description[1].trim();
                return new AddCommand(new ToDo(task, false));
            }
            case "deadline" -> {
                String[] deadlineDetails = description[1].split("/by ", 2);  // Split on "/by " with a limit of 2

                if (deadlineDetails.length < 2) {
                    System.out.println(description);
                    throw new ElmachoException("HELLOOO! When is the deadline??");
                }
                String task = deadlineDetails[0].trim();
                String dueDate = deadlineDetails[1].trim();  // This will be "2019-01-01 1800"

                return new AddCommand(new Deadline(task, dueDate, false));
            }
            case "event" -> {
                String[] eventDetails = description[1].split("/from ", 2);
                if (eventDetails.length < 2) {
                    throw new ElmachoException("HELLOOO! When does your event start??");
                }
                String task = eventDetails[0].trim();

                String[] timeDetails = eventDetails[1].split("/to ", 2);
                if (timeDetails.length < 2) {
                    throw new ElmachoException("HELLO. When does your event end?");
                }

                String from = timeDetails[0].trim();
                String to = timeDetails[1].trim();

                return new AddCommand(new Event(task, from, to, false));
            }
        }
        return new Command();
    }

    public static Command reloadTask(String command, String[] description) {
        String[] details = description[1].split("/");
        boolean isDone = details[0].equals("1");
        String storeDescription = details[1];

        switch (command) {
            case "T" -> {
                return new StoreCommand(new ToDo(storeDescription, isDone));
            }
            case "D" -> {
                String[] storeDetails = details[2].split("By");
                String dueDate = storeDetails[1].trim();
                return new StoreCommand(new Deadline(storeDescription, dueDate, isDone));
            }
            case "E" -> {
                String[] storeDetails = details[2].split("From");
                String[] storeDetails2 = storeDetails[1].split("To");
                String fromDate = storeDetails2[0].trim();
                String toDate = storeDetails2[1].trim();
                return new StoreCommand(new Event(storeDescription, fromDate, toDate, isDone));
            }
        }
        return new Command();
    }
}
