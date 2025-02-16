package ui;

import task.Task;
import task.Tasklist;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Ui {

    private final String NAME = "Elmacho";

    private String latestResponse;

    public Ui() {
        this.latestResponse = "";
    }

    public String getLatestResponse() {
        assert latestResponse != null : "Latest response should not be null";
        return latestResponse;
    }

    public String userGuide() {
        String userGuide = "USER GUIDE TO ELMACHO:\n\nTo add a ToDo:\n   'todo [task name]'"
                + "\nTo add a Deadline:\n   'deadline [task name] /by yyyy-MM-dd HHmm'"
                + "\nTo add an Event:\n   'event [task name] /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm'";
        String userCommands = "\n\nLIST OF USER COMMANDS:\n\n1.list\n2.delete\n3.mark\n4.unmark\n5.find";
        return userGuide + userCommands;
    }

    public String start() {
        return "What. I'm " + NAME + "\nWhat you want?";
    }

    public void help() {
        latestResponse = "Speak a language I can understand.";
    }

    public void printAddMessage(Tasklist tasklist, Task task) {
        assert task != null : "Task should not be null.";
        latestResponse = "Added task:\n  " + task + "\nNow you have " + tasklist.getNumberOfTasks()
                + " tasks in the list";
    }

    public void printDeleteMessage(Tasklist tasklist, Task task) {
        assert task != null : "Task should not be null.";
        latestResponse = "Deleted task:\n  " + task + "\nNow you have " + tasklist.getNumberOfTasks()
                + " tasks in the list";
    }

    public void printTaskList(Tasklist tasklist) {
        latestResponse = tasklist.getTasks().isEmpty()
                ? "List is empty"
                : "Your never ending list:\n\n" + IntStream.range(0, tasklist.getNumberOfTasks())
                .mapToObj(i -> (i + 1) + ". " + tasklist.getTasks().get(i))
                .collect(Collectors.joining("\n"));
    }

    public void printMarked(Task task) {
        assert task != null : "Task should not be null.";
        latestResponse = "Finally. Marked this task as done:\n  ["
                + task.getStatusIcon() + "] " + task.getDescription();
    }

    public void printUnmarked(Task task) {
        assert task != null : "Task should not be null.";
        latestResponse = "Another task not done...:\n  ["
                + task.getStatusIcon() + "] " + task.getDescription();
    }

    public void printFilteredTasklist(Tasklist tasklist, String keyword) {
        ArrayList<Task> tasks = tasklist.getTasks();
        if (tasks.isEmpty()) {
            latestResponse = "Nothing matched your keyword aw so sad";
        } else {
            latestResponse = IntStream.range(0, tasklist.getNumberOfTasks())
                    .filter(i -> tasklist.getTasks().get(i).getDescription().contains(keyword))
                    .mapToObj(i -> (i + 1) + ". " + tasklist.getTasks().get(i))
                    .collect(Collectors.joining("\n"));
        }
    }

    public void printArchivedTask(Tasklist tasklist, Tasklist archivedTasklist, Task task) {
        assert task != null : "Task should not be null.";
        latestResponse = "Archived task:\n  " + task + "\nNow you have " + archivedTasklist.getNumberOfTasks()
                + " tasks in the archived list,"
                + "\n"  + tasklist.getNumberOfTasks() + " tasks in the list.";
    }

    public void printUnarchivedTask(Tasklist tasklist, Task task) {
        assert task != null : "Task should not be null.";
        latestResponse = "Unarchived task:\n  " + task + "\nNow you have " + tasklist.getNumberOfTasks()
                + " tasks in the list";
    }
    public void printArchivedList(Tasklist tasklist) {
        latestResponse = tasklist.getTasks().isEmpty()
                ? "List is empty"
                : "You avoiding your problems by archiving tasks:\n\n" + IntStream.range(0, tasklist.getNumberOfTasks())
                .mapToObj(i -> (i + 1) + ". " + tasklist.getTasks().get(i))
                .collect(Collectors.joining("\n"));
    }
}
