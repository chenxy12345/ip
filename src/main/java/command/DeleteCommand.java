package command;

import task.Task;
import task.Tasklist;

import ui.Ui;

import java.util.ArrayList;
import exceptions.ElmachoException;

/**
 * Represents a command to delete a task from the tasklist and print the confirmation message to the UI.
 */
public class DeleteCommand extends Command {

    private int index;

    /**
     * Constructs a DeleteCommand with the index of the task to be deleted.
     * @param index The index of the task in tasklist to be deleted.
     *          Index starts from 1.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the delete command: removes the task from the task list and displays
     *                     a message on the UI indicating the task has been deleted.
     * @param tasklist The tasklist in which the task is being deleted from.
     * @param ui The UI used to print the delete message.
     * @throws ElmachoException if the task at the specified index does not exist.
     */
    @Override
    public void execute(Tasklist tasklist, Tasklist archivedTasklist, Ui ui) {
        assert index >= 1: "Index must be a positive integer.";

        try {
            ArrayList<Task> tasks = tasklist.getTasks();
            // Check if index is valid before getting the task
            if (index <= 0 || index > tasks.size()) {
                throw new ElmachoException("Invalid task number.");
            }
            Task task = tasks.get(index - 1);
            assert task != null: "Task should not be null.";
            tasklist.delete(index);
            ui.printDeleteMessage(tasklist, task);
        } catch (ElmachoException e) {
            System.out.println(e.getMessage());
        }
    }
}
