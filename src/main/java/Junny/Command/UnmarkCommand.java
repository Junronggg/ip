package Junny.Command;

import Junny.Storage;
import Junny.Tasks.Task;
import Junny.Ui;

import java.util.ArrayList;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void run(ArrayList<Task> tasks, Ui ui, Storage storage) {
        try {
            if (tasks.get(index - 1).isDone()) {
                tasks.get(index - 1).markAsNotDone();
                ui.markUndone(tasks.get(index - 1));
                storage.saveAllTasks(tasks);
            } else ui.printError("The task is not done yet, and you do not need to undo it!");
        } catch (NumberFormatException e) {
            // handle exception 3
            ui.printError("Please enter a valid number for unmark.");
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            // handle exception 3
            ui.printError("The task number you give does not exist. Please check again!");
        }
    }

}
