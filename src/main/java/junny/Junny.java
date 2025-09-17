package junny;

import java.util.ArrayList;

import junny.Ui.Ui;
import junny.command.Command;
import junny.tasks.Task;

/**
 * The main entry point of the Duke program.
 * Initializes necessary components and runs the application.
 */
public class Junny {
    private static Ui ui = new Ui();
    // store in D:\work\CS2103T\ip\data
    private static Storage storage = new Storage("./data/Junny.txt");
    private static ArrayList<Task> tasks = storage.loadAllTasks();
    private static Parser parser = new Parser(ui);
    private boolean lastResponseFalse = false;

    public boolean getLastResponseState() {
        return !lastResponseFalse;  // true if no error
    }
    /** For GUI: takes user input, returns response string */
    public String getResponse(String input) {
        Command command;
        try {
            command = parser.parse(input); // parsing may throw IllegalArgumentException
            lastResponseFalse = false;
        } catch (IllegalArgumentException e) {
            lastResponseFalse = true;
            return e.getMessage(); // return a friendly message to GUI
        }
        assert command != null : "Sorry but I don't understand that command :(";
        if (command == null) { // input was invalid
            lastResponseFalse = true;
            return "OOPS!!! I'm sorry, but I don't know what that means :(";
        }

        try {
            command.run(tasks, ui, storage); // run the command
            return ui.currectMsg(); // return the latest message for GUI
        } catch (IllegalArgumentException e) {
            lastResponseFalse = true;
            return e.getMessage();
        }

    }

    public Ui getUi() {
        return ui;
    }

    public static void main(String[] args) {
        ui.printHi();
        Parser parser = new Parser(ui);

        while (true) {
            // process: get
            String userInput = ui.readCommands();
            Command command = parser.parse(userInput);

            if (command == null) {
                continue; // invalid input handled in parser
            }

            command.run(tasks, ui, storage);

            if (command.isExit()) {
                break;
            }
        }
    }
}
