package command;

import task.Task;
import task.Tasklist;

import ui.Ui;

/**
 * Represents a command to mark a task as completed and print the marked confirmation message to the UI.
 */
public class MarkCommand extends Command {

    private int n;

    /**
     * Constructs a MarkCommand with the index of the task to be marked.
     * @param n The index of the task in the tasklist to be marked.
     */
    public MarkCommand(int n) {
        this.n = n;
    }

    /**
     * Executes the mark command: adds a "X" to the [ ] next to the task description
     *                           and displays the marked task on the UI.
     * @param tasklist The tasklist that contains the task to be marked.
     * @param ui The UI used to print the marked message.
     */
    public void execute(Tasklist tasklist, Ui ui) {
        tasklist.mark(n - 1);
        Task[] tasks = tasklist.getTasks();
        Task task = tasks[n - 1];
        ui.printMarked(task);
    }
}
