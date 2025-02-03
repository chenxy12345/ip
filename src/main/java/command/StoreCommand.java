package command;

import task.Deadline;
import task.Event;
import task.Task;
import task.Tasklist;
import task.ToDo;
import ui.Ui;

public class StoreCommand extends Command {

    private Task task;

    public StoreCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(Tasklist tasklist, Ui ui) {
        tasklist.add(task);
    }
}
