public class ExitCommand extends Command {

    public ExitCommand() {}

    @Override
    public void execute(Tasklist tasklist, Ui ui) {
        ui.printExit();
    }
}
