package Junny;

import java.util.ArrayList;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void run(ArrayList<Task> tasks, Ui ui, Storage storage) {
        try {
            ui.deleteTask(tasks.get(index - 1), tasks.size() - 1);
            tasks.remove(tasks.get(index - 1));
            storage.saveAllTasks(tasks);

        } catch (NumberFormatException e) {
            // handle exception 3
            ui.printError("Please enter a valid number for delete.");
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            // handle exception 3
            ui.printError("The task number you give does not exist. Please check again!");
        }
    }
}
