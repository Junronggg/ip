package junny.command;

import java.util.ArrayList;

import junny.Storage;
import junny.Ui.Ui;
import junny.tasks.Event;
import junny.tasks.Task;


public class EventCommand extends Command {
    private String description;
    private String from;
    private String to;

    public EventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public void run(ArrayList<Task> tasks, Ui ui, Storage storage) {
        Event event = new Event(description, from, to);
        tasks.add(event);
        ui.addTask(event, tasks.size());
        storage.saveAllTasks(tasks);
    }
}
