package command;

import task.Tasklist;

import ui.Ui;


public class Command {

    public Command() {}

    public void execute(Tasklist tasklist, Ui ui) {
        ui.help();
    }
}
