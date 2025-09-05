package Junny.Command;

import Junny.Tasks.Event;
import Junny.Storage;
import Junny.Tasks.Task;
import Junny.Ui;

import java.util.ArrayList;

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
