package junny.command;

import junny.Storage;
import junny.Ui.Ui;
import junny.tasks.Task;

import java.util.ArrayList;

public class SortCommand extends Command {
    @Override
    public void run(ArrayList<Task> tasks, Ui ui, Storage storage) {
        ui.printSorted(tasks);
    }
}
