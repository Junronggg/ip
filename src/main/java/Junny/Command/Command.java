package Junny.Command;

import Junny.Storage;
import Junny.Tasks.Task;
import Junny.Ui;

import java.util.ArrayList;

public abstract class Command {
    public abstract void run(ArrayList<Task> tasks, Ui ui, Storage storage);

    public boolean isExit() {
        return false;
    } // only overridden by Junny.Junny.Junny.Junny.Command.Command.ByeCommand
}
