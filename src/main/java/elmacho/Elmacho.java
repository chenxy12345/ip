package elmacho;

import javafx.application.Platform;
import task.Tasklist;

import command.Command;
import command.ExitCommand;

import exceptions.ElmachoException;

import ui.Ui;
import parser.Parser;
import storage.Storage;


public class Elmacho {

    private final Storage storage;
    private final Ui ui;
    private Tasklist tasklist;
    private Parser parser;

    public Elmacho() {
        this.ui = new Ui();
        this.storage = new Storage("Elmacho.txt");
        this.tasklist = storage.load();
        this.parser = new Parser();
        ui.start();
    }

    public Tasklist getTasklist() {
        return tasklist;
    }

    public Ui getUi() {
        return ui;
    }


    public String getResponse(String input) {
        try {
            Command command = parser.parse(input);

            // Execute the command and capture the response
            command.execute(tasklist, ui);
            storage.updateList(tasklist);

            // Return the latest UI response
            return ui.getLatestResponse();

        } catch (ElmachoException e) {
            return e.getMessage();
        }
    }

    public String getWelcomeMessage() {
        return ui.start();
    }

    public String getUserGuide() {
        return ui.userGuide();
    }
}

