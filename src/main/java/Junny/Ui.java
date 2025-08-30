package Junny;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String readCommands() {
        return this.scanner.nextLine();
    }

    public void printLine() {
        System.out.println("____________________________________________________________");
    }

    public void printHi() {
        printLine();
        System.out.println(" Hello! I'm Junny");
        System.out.println(" What can I do for you?");
        System.out.println("todo: todo x; deadline: deadline y /by yyyy-mm-dd; event: event x /from yyyy-mm-dd /to yyyy-mm-dd");
        printLine();
    }

    public void printBye() {
        System.out.println(" Bye. Hope to see you again soon!");
        printLine();
    }

    public void printError(String msg) {
        printLine();
        System.out.println("OOPS!!!" + msg);
        printLine();
    }

    public void printAllTasks(ArrayList<Task> tasks, int count) {
        printLine();
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < count; i++) {
            System.out.println(tasks.get(i).toString());
        }
        printLine();
    }

    public void addTask(Task task, int count) {
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.printf("Now you have %d tasks in the list.\n", count);
        printLine();
    }

    public void markDone(Task task) {
        printLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
        printLine();
    }

    public void markUndone(Task task) {
        printLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task.toString());
        printLine();
    }

    public void deleteTask(Task task, int count) {
        printLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        System.out.printf("Now you have %d tasks in the list.\n", count);
        printLine();
    }

    public void printTaskOnDate(LocalDate date, ArrayList<Task> tasks) {
        printLine();
        System.out.println("Here are the tasks in your list on the day: ");
        boolean hasTask = false;
        for (Task t : tasks) {
            if (t instanceof Deadline) {
                if (((Deadline) t).getBy().equals(date)) {
                    System.out.println(t);
                    hasTask = true;
                }
            } else if (t instanceof Event) {
                LocalDate from = ((Event) t).getStart();
                LocalDate to = ((Event) t).getEnd();
                if (!date.isBefore(from) && !date.isAfter(to)) {
                    System.out.println(t);
                    hasTask = true;
                }
            }
        }
        if (!hasTask) {
            System.out.println("No tasks on this date.");
        }
        printLine();
    }

    public void findResults(ArrayList<Task> tasksToPrint) {
        if (tasksToPrint.isEmpty()) {
            printLine();
            System.out.println("There is no matching task :(");
            printLine();
        } else {
            printLine();
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < tasksToPrint.size(); i++) {
                System.out.println((i + 1) + "." + tasksToPrint.get(i).toString());
            }
            printLine();
        }
    }
}
