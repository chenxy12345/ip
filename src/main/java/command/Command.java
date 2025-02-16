package command;

import task.Tasklist;

import ui.Ui;


public class Command {

    public Command() {
    }

    public void execute(Tasklist tasklist, Tasklist archivedTasklist, Ui ui) {
        ui.help();
    }
}
