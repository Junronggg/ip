import java.util.ArrayList;

public class ByeCommand extends Command{
    @Override
    public void run(ArrayList<Task> tasks, Ui ui, Storage storage) {
        ui.printBye();
    }

    @Override
    public boolean isExit() {
        return true; // program should stop
    }
}
