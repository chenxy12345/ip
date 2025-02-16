package storage;

import task.Task;
import task.Tasklist;

import command.Command;

import exceptions.ElmachoException;

import ui.Ui;
import parser.Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles loading and updating of  stored task database.
 * <p> This class loads the saved tasklist when the user starts the system a
 *      and updates the stored database upon exit. </p>
 */
public class Storage {

    private final String fileName;
    private final Ui ui;

    /**
     * Creates a Storage for the tasks
     * @param fileName Name of the file used to store tasks.
     */
    public Storage(String fileName) {
        this.fileName = fileName;
        this.ui = new Ui();
    }

    /**
     * Updates the stored tasklist to the latest added task.
     * @param tasklist The tasklist used to store tasks.
     */
    public void updateList(Tasklist tasklist) {
        ArrayList<Task> tasks = tasklist.getTasks();
        assert fileName != null : "File must not be null.";
        try (FileWriter writer = new FileWriter(this.fileName, false)) {
            for (Task task : tasks) {
                writer.write(task.getInfo() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Unable to update list");
        }
    }

    /**
     * Loads the data from the file when the chatbot starts up.
     * @return The tasklist stored.
     */
    public Tasklist load() {
        File file = new File(fileName);
        Parser parser = new Parser();
        Tasklist tasklist = new Tasklist();

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Unable to create new file");
            }
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Command command = parser.parse(line);

                assert command != null : "Command must not be null.";
                command.execute(tasklist, this.ui);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find file");
        } catch (ElmachoException e) {
            System.out.println(e.getMessage());
        }
        return tasklist;
    }
}
