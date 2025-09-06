package junny.command;

import java.util.ArrayList;

import junny.Storage;
import junny.Ui.Ui;
import junny.tasks.Deadline;
import junny.tasks.Task;



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
