package Junny;

import java.util.ArrayList;

public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void run(ArrayList<Task> tasks, Ui ui, Storage storage) {
        try {
            // VERY IMPORTANT: mark 2 extract 2, but it actually mark tasks[1]!!!
            // throw exception 3: may throw ArrayIndexOutOFBoundsException
            tasks.get(index - 1).markAsDone();
            ui.markDone(tasks.get(index - 1));
            storage.saveAllTasks(tasks);
        } catch (NumberFormatException e) {
            // handle exception 3
            ui.printError("Please enter a valid number for mark.");
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            // handle exception 3
            ui.printError("The task number you give does not exist. Please check again!");
        }
    }
}
