package Junny.Command;

import Junny.Storage;
import Junny.Tasks.Task;
import Junny.Tasks.Todo;
import Junny.Ui;

import java.util.ArrayList;

public class TodoCommand extends Command {
    private  String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void run(ArrayList<Task> tasks, Ui ui, Storage storage) {
        Todo todo = new Todo(description);
        tasks.add(todo);
        ui.addTask(todo, tasks.size());
        storage.saveAllTasks(tasks);
    }
}
