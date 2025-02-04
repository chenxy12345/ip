package task;

import exceptions.ElmachoExceptions;

/**
 * This class contains methods for managing tasks in a Tasklist.
 */
public class Tasklist {

    private Task[] arrayA;
    private int numberOfTasks;

    public Tasklist() {
        this.arrayA = new Task[100];
        this.numberOfTasks = 0;
    }

    public int getNumberOfTasks() {
        return numberOfTasks;
    }

    public Task[] getTasks() {
        return arrayA;
    }

    /**
     * Adds a task to the Tasklist.
     * @param task the Task object to be added
     */
    public void add(Task task) {
        this.arrayA[this.numberOfTasks] = task;
        this.numberOfTasks++;
    }

    /**
     * Deletes a task from the Tasklist.
     * @param n The index of the task to be deleted.
     * @throws ElmachoExceptions if the index is not on the tasklist.
     */
    public void delete(int n) throws ElmachoExceptions {
        if (n == 0) {
            this.arrayA[0] = null;
        }
        if (n > 0 && n <= this.numberOfTasks) {
            for (int i = n - 1; i < this.numberOfTasks; i++) {
                this.arrayA[i] = this.arrayA[i + 1];
            }
            this.numberOfTasks--;
        } else {
            throw new ElmachoExceptions("Invalid task number.");
        }
    }

    /**
     * Puts an "X" in the checkbox of the task.
     * @param n The index of the task to be marked.
     */
    public void mark(int n) {
        if (n >= 0 && n < this.numberOfTasks) {
            this.arrayA[n].mark();
        }
    }

    /**
     * Removes any "X" from the checkbox.
     * @param n The index of the task to be unmarked.
     */
    public void unmark(int n) {
        if (n >= 0 && n < this.numberOfTasks) {
            this.arrayA[n].unmark();
        }
    }

    public Tasklist find(String keyword) {
        Tasklist filteredList = new Tasklist();
        for (int i = 0; i < this.numberOfTasks; i++) {
            String[] word = arrayA[i].getDescription().split(" ");
            for (int j = 0; j < word.length; j++) {
                if (word[j].equals(keyword)) {
                    filteredList.add(arrayA[i]);
                }
            }
        }
        return filteredList;
    }
}
