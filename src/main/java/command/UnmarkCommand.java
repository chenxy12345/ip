package command;

import task.Task;
import task.Tasklist;

import ui.Ui;

import java.util.ArrayList;

/**
 * Represents a command to mark a task as uncompleted and print the unmarked confirmation message to the UI.
 */
public class UnmarkCommand extends Command {

    private final int index;

    /**
     * Constructs an UnmarkCommand with the index of the task to be unmarked.
     * @param index The index of the task in the tasklist to be unmarked.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the unmark command: removes any "X" in the [ ] next to the task description
     *                              and displays the unmarked task on the UI.
     * @param tasklist The tasklist that contains the task to be unmarked.
     * @param ui The UI used to print the unmarked message.
     */
    public void execute(Tasklist tasklist, Ui ui) {
        assert index >= 1 : "Index must be a positive integer.";
        tasklist.unmark(index - 1);
        ArrayList<Task> tasks = tasklist.getTasks();
        Task task = tasks.get(index - 1);

        assert task != null : "Task should not be null.";
        ui.printUnmarked(task);
    }
}
