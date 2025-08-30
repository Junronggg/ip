package Junny;

import java.util.ArrayList;

public class JunnyOop {
    static Ui ui = new Ui();
    static Storage storage = new Storage("./data/Junny.Junny.txt");
    static ArrayList<Task> tasks = storage.loadAllTasks();

    public static void main(String[] args) {
        ui.printHi();
        Parser parser = new Parser(ui);

        while (true) {
            // process: get
            String userInput = ui.readCommands();
            Command command = parser.parse(userInput);

            if (command == null) continue; // invalid input handled in parser

            command.run(tasks, ui, storage);

            if (command.isExit()) break;
        }
    }
}
