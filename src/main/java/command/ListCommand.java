package command;

import task.Deadline;
import task.Event;
import task.Task;
import task.Tasklist;
import task.ToDo;
import ui.Ui;

public class ListCommand extends Command {

    public ListCommand() {}

    @Override
    public void execute(Tasklist tasklist, Ui ui) {
        ui.printTaskList(tasklist);
    }
}
