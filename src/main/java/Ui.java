public class Ui {

    public Ui() {
    }

    public void start() {
        String name = "Elmacho";
        System.out.println("____________________________________________________________");
        System.out.println("What. I'm " + name);
        System.out.println("What you want?");
        System.out.println("____________________________________________________________");
    }

    public void help() {
        System.out.println("Speak a language I can understand.");
        System.out.println("____________________________________________________________");
    }

    public void printAddMessage(Tasklist tasklist, Task task) {
        System.out.println("Added task:\n  " + task + "\nNow you have " + tasklist.getNumberOfTasks() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    public void printDeleteMessage(Tasklist tasklist, Task task) {
        System.out.println("Deleted task:\n  " + task + "\nNow you have " + tasklist.getNumberOfTasks() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    public void printTaskList(Tasklist tasklist) {
        Task[] arrayA = tasklist.getTasks();
        for (int i = 0; i < tasklist.getNumberOfTasks(); i++) {
            System.out.println(i + 1 + "." + arrayA[i].toString());
        }
        if (tasklist.getNumberOfTasks() == 0) {
            System.out.println("List is empty");
        }
        System.out.println("____________________________________________________________");
    }

    public void printMarked(Task task) {
        System.out.println("Finally. Marked this task as done:\n  ["
                + task.getStatusIcon() + "] " + task.getDescription());
        System.out.println("____________________________________________________________");
    }

    public void printUnmarked(Task task) {
        System.out.println("Another task not done...:\n  ["
                + task.getStatusIcon() + "] " + task.getDescription());
        System.out.println("____________________________________________________________");
    }

    public void printExit() {
        System.out.println("OkBye.");
        System.out.println("____________________________________________________________");
    }

}
