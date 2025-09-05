package Junny.Command;

import Junny.Tasks.Deadline;
import Junny.Storage;
import Junny.Tasks.Task;
import Junny.Ui;

import java.util.ArrayList;

public class DeadlineCommand extends Command {
    private String description;
    private String by;

    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void run(ArrayList<Task> tasks, Ui ui, Storage storage) {
        Deadline ddl = new Deadline(this.description, this.by);
        tasks.add(ddl);
        ui.addTask(ddl, tasks.size());
        storage.saveAllTasks(tasks);
    }
}
