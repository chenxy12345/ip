package command;

import task.Deadline;
import task.Event;
import task.Task;
import task.Tasklist;
import task.ToDo;
import ui.Ui;

public class AddCommand extends Command {

    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(Tasklist tasklist, Ui ui) {
        tasklist.add(this.task);
        ui.printAddMessage(tasklist, this.task);
    }
}
