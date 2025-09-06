package junny.command;

import java.util.ArrayList;

import junny.Storage;
import junny.Ui.Ui;
import junny.tasks.Task;

public class ListCommand extends Command {
    @Override
    public void run(ArrayList<Task> tasks, Ui ui, Storage storage) {
        ui.printAllTasks(tasks, tasks.size());
    }
}
