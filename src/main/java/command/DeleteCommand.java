package command;

import task.Task;
import task.Tasklist;

import ui.Ui;

import exceptions.ElmachoExceptions;

/**
 * Represents a command to delete a task from the tasklist and print the confirmation message to the UI.
 */
public class DeleteCommand extends Command {

    private int n;

    /**
     * Constructs a DeleteCommand with the index of the task to be deleted.
     * @param n The index of the task in tasklist to be deleted.
     *          Index starts from 1.
     */
    public DeleteCommand(int n) {
        this.n = n;
    }

    /**
     * Executes the delete command: removes the task from the task list and displays
     *                     a message on the UI indicating the task has been deleted.
     * @param tasklist The tasklist in which the task is being deleted from.
     * @param ui The UI used to print the delete message.
     * @throws ElmachoExceptions if the task at the specified index does not exist.
     */
    @Override
    public void execute(Tasklist tasklist, Ui ui) {
        Task[] tasks = tasklist.getTasks();
        Task task = tasks[n - 1];
        try {
            tasklist.delete(n);
            ui.printDeleteMessage(tasklist, task);
        } catch (ElmachoExceptions e) {
            System.out.println(e.getMessage());
        }
    }
}
