import java.util.ArrayList;

public class ListCommand extends Command{
    @Override
    public void run(ArrayList<Task> tasks, Ui ui, Storage storage){
        ui.printAllTasks(tasks, tasks.size());
    }
}
