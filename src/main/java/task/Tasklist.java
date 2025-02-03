package task;

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

    public void add(Task task) {
        this.arrayA[this.numberOfTasks] = task;
        this.numberOfTasks++;
    }

    public void delete(int n) {
        if (n == 0) {
            this.arrayA[0] = null;
        }
        if (n > 0 && n <= this.numberOfTasks) {
            for (int i = n - 1; i < this.numberOfTasks; i++) {
                this.arrayA[i] = this.arrayA[i + 1];
            }
            this.numberOfTasks--;
        } else {
            System.out.println("Invalid task number.");
        }
    }

    public void mark(int n) {
        if (n >= 0 && n < this.numberOfTasks) {
            this.arrayA[n].mark();
        }
    }

    public void unmark(int n) {
        if (n >= 0 && n < this.numberOfTasks) {
            this.arrayA[n].unmark();
        }
    }
}
