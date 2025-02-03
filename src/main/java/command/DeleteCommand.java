package command;

import task.Deadline;
import task.Event;
import task.Task;
import task.Tasklist;
import task.ToDo;
import ui.Ui;


public class DeleteCommand extends Command {

    private int n;

    public DeleteCommand(int n) {
        this.n = n;
    }

    @Override
    public void execute(Tasklist tasklist, Ui ui) {
        Task[] tasks = tasklist.getTasks();
        Task task = tasks[n - 1];
        tasklist.delete(n);
        ui.printDeleteMessage(tasklist, task);
    }
}
