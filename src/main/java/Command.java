import java.util.ArrayList;

public abstract class Command {
    public abstract void run(ArrayList<Task> tasks, Ui ui, Storage storage);

    public boolean isExit() {
        return false;
    } // only overridden by ByeCommand
}
