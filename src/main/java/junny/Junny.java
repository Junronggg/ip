package junny;

import java.util.ArrayList;

import junny.command.Command;
import junny.tasks.Task;

/**
 * The main entry point of the Duke program.
 * Initializes necessary components and runs the application.
 */
public class Junny {
    private static Ui ui = new Ui();
    // store in D:\work\CS2103T\ip\data
    private static Storage storage = new Storage("./data/JunnyOop.txt");
    private static ArrayList<Task> tasks = storage.loadAllTasks();

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
