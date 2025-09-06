package junny.command;

import java.util.ArrayList;

import junny.Storage;
import junny.Ui.Ui;
import junny.tasks.Task;


/***
 * ByeCommand class to break the loop and exit the programme
 */
public class ByeCommand extends Command {
    @Override
    public void run(ArrayList<Task> tasks, Ui ui, Storage storage) {
        ui.printBye();
    }

    @Override
    public boolean isExit() {
        return true; // program should stop
    }
}
