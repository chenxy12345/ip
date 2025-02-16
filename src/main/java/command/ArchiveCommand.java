package command;

import exceptions.ElmachoException;
import task.Task;
import task.Tasklist;
import ui.Ui;

import java.util.ArrayList;

public class ArchiveCommand extends Command{

    private final int index;
    
    public ArchiveCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(Tasklist tasklist, Tasklist archivedTasklist, Ui ui) {
        try {
            ArrayList<Task> tasks = tasklist.getTasks();
            if (index <= 0 || index > tasks.size()) {
                throw new ElmachoException("Invalid task number. Change it.");
            }
            Task task = tasks.get(index - 1);
            assert task != null : "Task should not be null.";

            archivedTasklist.add(task);
            tasklist.delete(index);
            ui.printArchivedTask(tasklist, archivedTasklist, task);
        } catch (ElmachoException e) {
            System.out.println(e.getMessage());
        }
    }
}
