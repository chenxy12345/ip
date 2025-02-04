package command;

import task.Task;
import task.Tasklist;

import ui.Ui;

/**
 * Represents a command to mark a task as uncompleted and print the unmarked confirmation message to the UI.
 */
public class UnmarkCommand extends Command {

    private int n;

    /**
     * Constructs an UnmarkCommand with the index of the task to be unmarked.
     * @param n The index of the task in the tasklist to be unmarked.
     */
    public UnmarkCommand(int n) {
        this.n = n;
    }

    /**
     * Executes the unmark command: removes any "X" in the [ ] next to the task description
     *                              and displays the unmarked task on the UI.
     * @param tasklist The tasklist that contains the task to be unmarked.
     * @param ui The UI used to print the unmarked message.
     */
    public void execute(Tasklist tasklist, Ui ui) {
        tasklist.unmark(n - 1);
        Task[] tasks = tasklist.getTasks();
        Task task = tasks[n - 1];
        ui.printUnmarked(task);
    }
}
