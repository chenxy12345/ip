public class UnmarkCommand extends Command{

    private int n;

    public UnmarkCommand(int n) {
        this.n = n;
    }

    public void execute(Tasklist tasklist, Ui ui) {
        tasklist.unmark(n - 1);
        Task[] tasks = tasklist.getTasks();
        Task task = tasks[n - 1];
        ui.printUnmarked(task);
    }
}
