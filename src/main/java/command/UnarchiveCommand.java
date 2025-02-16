package command;

import exceptions.ElmachoException;
import task.Task;
import task.Tasklist;
import ui.Ui;

import java.util.ArrayList;

public class UnarchiveCommand extends Command {

    private final int index;

    public UnarchiveCommand(int index) {
        this.index = index;
    }

    public void execute(Tasklist tasklist, Tasklist archivedTasklist, Ui ui) {
        try {
            ArrayList<Task> tasks = archivedTasklist.getTasks();
            Task task = tasks.get(index - 1);
            assert task != null : "Task should not be null.";

            tasklist.add(task);
            archivedTasklist.delete(index);
            ui.printUnarchivedTask(tasklist, task);
        } catch (ElmachoException e) {
            System.out.println(e.getMessage());
        }
    }
}
