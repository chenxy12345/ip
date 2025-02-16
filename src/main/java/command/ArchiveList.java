package command;

import task.Tasklist;
import ui.Ui;

public class ArchiveList extends Command {

    public ArchiveList() {}

    /**
     * Executes the list command: displays the tasks in the tasklist.
     * @param tasklist The tasklist to be displayed.
     * @param ui The UI used to print the tasks.
     */
    @Override
    public void execute(Tasklist tasklist, Tasklist archivedTasklist, Ui ui) {
        ui.printArchivedList(archivedTasklist);
    }
}
