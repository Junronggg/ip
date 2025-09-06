package junny.Ui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import junny.tasks.Deadline;
import junny.tasks.Event;
import junny.tasks.Task;

/**
 * Handles interactions with the user
 * Reading input and displaying output messages. (not responsible for modifying local file!)
 */
public class Ui {
    private Scanner scanner;
    private String currentOutput;

    /**
     * Creates a Ui object with an input scanner.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String currectMsg() {
        return currentOutput;
    }

    /**
     * Reads the next line of input from the user.
     *
     * @return The user's input as a string
     */
    public String readCommands() {
        return this.scanner.nextLine();
    }

    public void printLine() {
        System.out.println("____________________________________________________________");
    }

    public void printHi() {
        printLine();
        currentOutput = "Hello! I'm Junny!\n"
                + "What can I do for you?\n"
                + "todo: todo x;\n deadline: deadline y /by yyyy-mm-dd;\n event: event x /from yyyy-mm-dd /to yyyy-mm-dd";
        System.out.println(currentOutput);
        printLine();
    }

    public void printBye() {
        currentOutput = "Bye. Hope to see you again soon!";
        System.out.println(currentOutput);
        printLine();

    }

    public void printError(String msg) {
        currentOutput = "OOPS!!! " + msg;
        printLine();
        System.out.println(currentOutput);
        printLine();

    }

    public void printAllTasks(ArrayList<Task> tasks, int count) {
        printLine();
        currentOutput = "Here are the tasks in your list: \n";
        for (int i = 0; i < count; i++) {
            currentOutput += tasks.get(i).toString() + "\n";
        }
        System.out.println(currentOutput);
        printLine();

    }

    public void addTask(Task task, int count) {
        printLine();
        currentOutput = "Got it. I've added this task: \n" + task.toString();
        String line = String.format("Now you have %d tasks in the list.\n", count);
        currentOutput += line;
        System.out.println(currentOutput);
        printLine();
    }

    public void markDone(Task task) {
        printLine();
        currentOutput = "Nice! I've marked this task as done:\n" + task.toString();
        System.out.println(currentOutput);
        printLine();
    }

    public void markUndone(Task task) {
        printLine();
        currentOutput = "OK, I've marked this task as not done yet:\n" + task.toString();
        System.out.println(currentOutput);
        printLine();
    }

    public void deleteTask(Task task, int count) {
        printLine();
        String line = String.format("Now you have %d tasks in the list.\n", count);
        currentOutput = "Noted. I've removed this task:"
                + task.toString()
                + line;
        System.out.println(currentOutput);
        printLine();
    }

    public void printTaskOnDate(LocalDate date, ArrayList<Task> tasks) {
        printLine();
        currentOutput = "Here are the tasks in your list on the day: \n";
        boolean hasTask = false;
        for (Task t : tasks) {
            if (t instanceof Deadline) {
                if (((Deadline) t).getBy().equals(date)) {
                    System.out.println(t);
                    hasTask = true;
                    currentOutput += t + "\n";
                }
            } else if (t instanceof Event) {
                LocalDate from = ((Event) t).getStart();
                LocalDate to = ((Event) t).getEnd();
                if (!date.isBefore(from) && !date.isAfter(to)) {
                    System.out.println(t);
                    hasTask = true;
                    currentOutput += t + "\n";
                }
            }
        }
        if (!hasTask) {
            currentOutput += "No tasks on this date.";
        }
        System.out.println(currentOutput);
        printLine();
    }

    public void findResults(ArrayList<Task> tasksToPrint) {
        if (tasksToPrint.isEmpty()) {
            printLine();
            currentOutput = "There is no matching task :(";
            System.out.println(currentOutput);
            printLine();
        } else {
            printLine();
            currentOutput = "Here are the matching tasks in your list: \n";
            for (int i = 0; i < tasksToPrint.size(); i++) {
                currentOutput += tasksToPrint.get(i).toString() + "\n";
                System.out.println((i + 1) + "." + tasksToPrint.get(i).toString());
            }
            System.out.println(currentOutput);
            printLine();
        }
    }
}
