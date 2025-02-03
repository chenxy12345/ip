package command;

import task.Deadline;
import task.Event;
import task.Task;
import task.Tasklist;
import task.ToDo;
import ui.Ui;

public class MarkCommand extends Command {

    private int n;

    public MarkCommand(int n) {
        this.n = n;
    }

    public void execute(Tasklist tasklist, Ui ui) {
        tasklist.mark(n - 1);
        Task[] tasks = tasklist.getTasks();
        Task task = tasks[n - 1];
        ui.printMarked(task);
    }
}
