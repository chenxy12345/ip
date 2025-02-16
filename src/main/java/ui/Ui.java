package ui;

import task.Task;
import task.Tasklist;

import java.util.ArrayList;

public class Ui {

    private final String NAME = "elmacho.Elmacho";

    public Ui() {
    }

    public void start() {
        System.out.println("____________________________________________________________");
        System.out.println("What. I'm " + NAME);
        System.out.println("What you want?");
        System.out.println("____________________________________________________________");
    }

    public void help() {
        System.out.println("Speak a language I can understand.");
        System.out.println("____________________________________________________________");
    }

    public void printAddMessage(Tasklist tasklist, Task task) {
        System.out.println("Added task:\n  " + task + "\nNow you have " + tasklist.getNumberOfTasks() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    public void printDeleteMessage(Tasklist tasklist, Task task) {
        System.out.println("Deleted task:\n  " + task + "\nNow you have " + tasklist.getNumberOfTasks() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    public void printTaskList(Tasklist tasklist) {
        ArrayList<Task> tasks = tasklist.getTasks();
        int i = 1;
        for (Task task : tasks) {
            System.out.println(i + "." + task.toString());
            i++;
        }
        if (tasks.isEmpty()) {
            System.out.println("List is empty");
        }
        System.out.println("____________________________________________________________");
    }

    public void printMarked(Task task) {
        System.out.println("Finally. Marked this task as done:\n  ["
                + task.getStatusIcon() + "] " + task.getDescription());
        System.out.println("____________________________________________________________");
    }

    public void printUnmarked(Task task) {
        System.out.println("Another task not done...:\n  ["
                + task.getStatusIcon() + "] " + task.getDescription());
        System.out.println("____________________________________________________________");
    }

    public void printFilteredTasklist(Tasklist tasklist) {
        ArrayList<Task> tasks = tasklist.getTasks();
        if (tasks.isEmpty()) {
            System.out.println("Nothing matched your keyword aw so sad");
            System.out.println("____________________________________________________________");
            return;
        }
        System.out.println("Here are the matching tasks in your list:");
        int i = 1;
        for (Task task : tasks) {
            System.out.println(i + "." + task.toString());
            i++;
        }
        System.out.println("____________________________________________________________");
    }

    public void printExit() {
        System.out.println("OkBye.");
        System.out.println("____________________________________________________________");
    }

}
