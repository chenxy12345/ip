package command;

import task.Deadline;
import task.Event;
import task.Task;
import task.Tasklist;
import task.ToDo;
import ui.Ui;

public class ExitCommand extends Command {

    public ExitCommand() {}

    @Override
    public void execute(Tasklist tasklist, Ui ui) {
        ui.printExit();
    }
}
