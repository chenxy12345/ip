package ui;

import task.Task;
import task.Tasklist;

import java.util.ArrayList;

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
        ArrayList<Task> tasks = tasklist.getTasks();
        if (tasks.isEmpty()) {
            latestResponse = "List is empty";
        } else {
            int i = 1;
            String result = "";
            for (Task task : tasks) {
                result = result + i + ". " + task + "\n";
                i++;
            }
            latestResponse = result;
        }
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

    public void printFilteredTasklist(Tasklist tasklist) {
        ArrayList<Task> tasks = tasklist.getTasks();
        if (tasks.isEmpty()) {
            latestResponse = "Nothing matched your keyword aw so sad";
        } else {
            int i = 1;
            String result = "";
            for (Task task : tasks) {
                result = result + i + ". " + task + "\n";
                i++;
            }
            latestResponse = result;
        }
    }

}
