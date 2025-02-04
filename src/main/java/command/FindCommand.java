package command;

import task.Task;
import task.Tasklist;

import ui.Ui;

public class FindCommand extends Command {

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(Tasklist tasklist, Ui ui) {
        Tasklist filteredList = tasklist.find(keyword);
        ui.printFilteredTasklist(filteredList);
    }
}
