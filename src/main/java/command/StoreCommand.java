package command;

import task.Task;
import task.Tasklist;

import ui.Ui;

/**
 * Represents a command to store a task into the storage tasklist.
 */
public class StoreCommand extends Command {

    private Task task;

    /**
     * Constructs a StoreCommand with the task object to be stored.
     * @param task The task to be stored in tasklist.
     */
    public StoreCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the store command: stores the task to the tasklist.
     * @param tasklist The tasklist that contains the tasks to be stored
     */
    @Override
    public void execute(Tasklist tasklist, Ui ui) {
        tasklist.add(task);
    }
}
