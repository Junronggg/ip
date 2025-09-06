package junny.command;

import java.util.ArrayList;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;
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

        // Wait 3 seconds before quitting
        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(event -> Platform.exit());
        delay.play();
    }

    @Override
    public boolean isExit() {
        return true; // program should stop
    }
}
