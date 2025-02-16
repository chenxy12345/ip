package elmacho;

import task.Tasklist;

import command.Command;
import command.ExitCommand;

import exceptions.ElmachoException;

import ui.Ui;
import parser.Parser;
import storage.Storage;

import java.util.Scanner;

public class Elmacho {

    private final Storage storage;
    private final Ui ui;
    private Tasklist tasklist;

    public Elmacho() {
        this.ui = new Ui();
        this.tasklist = new Tasklist();
        this.storage = new Storage("elmacho.Elmacho.txt");
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
            } catch (ElmachoException e) {
                System.out.println(e.getMessage());
            }

            if (command instanceof ExitCommand) {
                break;
            }
        }
        scanner.close();
    }
}

