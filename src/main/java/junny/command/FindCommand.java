package junny.command;

import java.util.ArrayList;

import junny.Storage;
import junny.Ui.Ui;
import junny.tasks.Task;

public class FindCommand extends Command {
    private final String searchWord;

    public FindCommand(String searchWord) {
        this.searchWord = searchWord;
    }

    @Override
    public void run(ArrayList<Task> tasks, Ui ui, Storage storage) {
        ArrayList<Task> result = new ArrayList<>();
        for (Task t : tasks) {
            if (t.toString().contains(searchWord)) {
                result.add(t);
            }
        }
        ui.findResults(result);
    }

}
