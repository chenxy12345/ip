package storage;

import command.Command;
import parser.Parser;
import task.Task;
import task.Tasklist;
import ui.Ui;
import exceptions.ElmachoExceptions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

    private String fileName;
    private Ui ui;

    public Storage(String fileName) {
        this.fileName = fileName;
        this.ui = new Ui();
    }

    public void updateList(Tasklist tasklist) {
        Task[] array = tasklist.getTasks();
        try (FileWriter writer = new FileWriter(this.fileName, false)) {
            for (int i = 0; i < tasklist.getNumberOfTasks(); i++) {
                writer.write(array[i].getInfo() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Unable to update list");
        }
    }

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
                command.execute(tasklist, this.ui);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find file");
        } catch (ElmachoExceptions e) {
            System.out.println(e.getMessage());
        }
        return tasklist;
    }
}
