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

    /** For GUI: takes user input, returns response string */
    public String getResponse(String input) {
        Command command = parser.parse(input);
        if (command == null) {
            return "I don't understand that command.";
        }
        command.run(tasks, ui, storage);  // run the command
        return ui.currectMsg();       // return the latest message for GUI
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
