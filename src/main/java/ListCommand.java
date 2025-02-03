public class ListCommand extends Command {

    public ListCommand() {}

    @Override
    public void execute(Tasklist tasklist, Ui ui) {
        ui.printTaskList(tasklist);
    }
}
