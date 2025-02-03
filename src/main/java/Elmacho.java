import java.util.Scanner;

public class Elmacho {

    private Storage storage;
    private Ui ui;
    private Tasklist tasklist;

    public Elmacho() {
        this.ui = new Ui();
        this.tasklist = new Tasklist();
        this.storage = new Storage("Elmacho.txt");
    }

    public Tasklist getTasklist() {
        return tasklist;
    }

    public Ui getUi() {
        return ui;
    }


    public static void main(String[] args) {
        Elmacho elmacho = new Elmacho();
        elmacho.ui.start();
        elmacho.tasklist = elmacho.storage.load();
        Scanner scanner = new Scanner(System.in);
        Parser parser = new Parser();

        while (true) {
            String line = scanner.nextLine();
            Command command = null;
            try {
                command = parser.parse(line);
                command.execute(elmacho.tasklist, elmacho.ui);
                elmacho.storage.updateList(elmacho.tasklist);
            } catch (ElmachoExceptions e) {
                System.out.println(e.getMessage());
            }

            if (command instanceof ExitCommand) {
                break;
            }
        }
        scanner.close();
    }
}

