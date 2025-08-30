package Junny;

import java.time.LocalDate;

/**
 * Parses user input into commands that the program can execute.
 */
public class Parser {
    public Ui ui;

    public Parser (Ui ui) {
        this.ui = ui;
    }

    /**
     * Parses the given input string and returns the corresponding command.
     *
     * @param userCommand The command entered by the user
     * @return A Command object representing the parsed instruction
     */
    public Command parse(String userCommand) {
        String[] inputByParts = userCommand.split(" ", 2);
        String commandWord = inputByParts[0].toUpperCase();
        String commandDetail = inputByParts.length > 1 ? inputByParts[1] : "";
        CommandTypes command = null;

        try {
            command = CommandTypes.valueOf(commandWord);
        } catch (IllegalArgumentException e) {
            // handle exception 2
            ui.printError("I'm sorry, but I don't know what that means :(");
            return null;
        } catch (Exception e) {
            ui.printError("I'm sorry, but I don't know what that means :(");
            return null;
        }

        switch (command) {
            case CommandTypes.BYE:
                return new ByeCommand();
            case CommandTypes.LIST:
                if (commandDetail.startsWith("/on")) {
                    try {
                        String dateStr = inputByParts[1].substring(4).trim(); // get the date string, which is 4th position
                        LocalDate targetDate = LocalDate.parse(dateStr); // yyyy-MM-dd
                        return new ListOnDateCommand(targetDate);
                    } catch (Exception e) {
                        ui.printError("Please enter the date in yyyy-MM-dd format.");
                    }
                } else {
                    return new ListCommand();
                }
            case CommandTypes.MARK:
                return new MarkCommand(Integer.valueOf(commandDetail));
            case CommandTypes.UNMARK:
                return new UnmarkCommand(Integer.valueOf(commandDetail));
            case CommandTypes.DELETE:
                return new DeleteCommand(Integer.valueOf(commandDetail));

            case CommandTypes.DEADLINE:
                // split to "read book" & "Sunday"
                String[] parts = commandDetail.split("/by", 2);
                // throw exception 5
                if (parts.length < 2) throw new IllegalArgumentException("deadline task must have a due time. Please follow deadline read /by xx.");
                String description = parts[0].trim();   // "read book"
                String by = parts[1].trim();    // "Sunday
                return new DeadlineCommand(description, by);

            case CommandTypes.EVENT:
                // split to "read book" & "/from xxx" (split on from)
                String[] parts1 = commandDetail.split("/from", 2);
                // throw exception 6
                if (parts1.length <2) throw new IllegalArgumentException("event task must have a from time. Please follow event read /from xx /to yy.");
                String eventDescription = parts1[0].trim();   // "read book"
                String fromTo = parts1[1].trim();    // "/from xxx /to xxx"
                // split to "from" & "to"
                String[] parts2 = fromTo.split("/to", 2);
                if (parts2.length <2) throw new IllegalArgumentException("event task must have a to time. Please follow event read /from xx /to yy.");
                String from = parts2[0].trim();
                String to = parts2[1].trim();
                return new EventCommand(eventDescription, from, to);

            case CommandTypes.TODO:
                return new TodoCommand(commandDetail);

            default:
                // handle exception 2
                throw new IllegalArgumentException("I'm sorry, but I don't know what that means :(");
        }

    }
}
