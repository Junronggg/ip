package junny.command;

import java.time.LocalDate;
import java.util.ArrayList;

import junny.Storage;
import junny.Ui;
import junny.tasks.Task;




public class ListOnDateCommand extends Command {
    private LocalDate date;

    public ListOnDateCommand(LocalDate date) {
        this.date = date;
    }

    @Override
    public void run(ArrayList<Task> tasks, Ui ui, Storage storage) {
        try {
            ui.printTaskOnDate(this.date, tasks);
        } catch (Exception e) {
            ui.printError("Please enter the date in yyyy-MM-dd format.");
        }
    }
}
