import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

// for file creation, updating (add, mark & unmark, delete), retrieving
// think Storage class as a storage warehouse, the only field needed is the address of the warehouse
public class Storage {
    private Path filePath;

    public Storage (String filePath) {
        // get the filePath
        this.filePath = Paths.get(filePath);
        try {
            // create folder if missing
            Files.createDirectories(this.filePath.getParent());
            // create file if missing
            if (!Files.exists(this.filePath)) {
                Files.createFile(this.filePath);
            }
        } catch (IOException e) {
            System.out.println("Error initializing storage: " + e.getMessage());
        }
    }

    // save all tasks to the file
    // saving the whole again every time: easier when coding, since for tasks like mark and unmark, need to delete & add again
    // can just simply change the task list, then save everything again
    public void saveAllTasks (ArrayList<Task> tasks) {
        List<String> lines = new ArrayList<>();
        for (Task t : tasks) {
            lines.add(t.toFileString());
        }
        try {
            Files.write(filePath, lines); // overwrites file
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    // load all tasks from the file
    public ArrayList<Task> loadAllTasks () {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            List<String> content = Files.readAllLines(filePath);
            for (String line : content) {
                tasks.add(Task.convertToTask(line));
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }
}
