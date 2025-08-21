import java.util.Scanner;

public class Junny {
    static Task[] tasks = new Task[100];
    static int count = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        printLine();
        System.out.println(" Hello! I'm Junny");
        System.out.println(" What can I do for you?");
        printLine();

        // all inputs: todo, deadline, event, list, mark, unmark
        while (true) {
            String userInput = scanner.nextLine();

            // handle the input
            // split to "ddl" and "read book \by Sunday" OR "mark" and "2"
            String[] inputByParts = userInput.split(" ", 2);
            if (userInput.equals("bye")) {
                printBye();
                break;
            } else if (userInput.equals("list")) {
                printAllTasks(tasks, count);
            } else if (inputByParts[0].equals("mark")) {
                int index = Integer.parseInt(inputByParts[1]) - 1;
                // VERY IMPORTANT: mark 2 extract 2, but it actually mark tasks[1]!!!
                markDone(tasks[index]);
            } else if (inputByParts[0].equals("unmark")) {
                int index = Integer.parseInt(inputByParts[1]) - 1;
                markUndone(tasks[index]);
            } else {
                // if is todo, deadline, event: handle input first
                String details = inputByParts[1];

                if (inputByParts[0].equals("deadline")) {
                    // split to "redd book" & "Sunday"
                    String[] parts = details.split("/by", 2);
                    String description = parts[0].trim();   // "read book"
                    String by = parts[1].trim();    // "Sunday

                    Deadline ddl = new Deadline(description, by);
                    addTask(ddl);
                } else if (inputByParts[0].equals("event")) {
                    // split to "read book" & "/from xxx" (split on from)
                    String[] parts1 = details.split("/from", 2);
                    String description = parts1[0].trim();   // "read book"
                    String fromTo = parts1[1].trim();    // "/from xxx /to xxx"
                    // split to "from" & "to"
                    String[] parts2 = fromTo.split("/to", 2);
                    String from = parts2[0].trim();
                    String to = parts2[1].trim();

                    Event event = new Event(description, from, to);
                    addTask(event);
                } else {
                    Todo todo = new Todo(details);
                    addTask(todo);
                }

            }
        }
        scanner.close();
    }

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public static void printAllTasks(Task[] tasks, int count) {
        printLine();
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < count; i++) {
            System.out.println(tasks[i].toString());
        }
        printLine();
    }

    public static void printBye() {
        System.out.println(" Bye. Hope to see you again soon!");
        printLine();
    }

    public static void markDone(Task task) {
        printLine();
        System.out.println("Nice! I've marked this task as done:");
        task.markAsDone();
        System.out.println(task.toString());
        printLine();
    }

    public static void markUndone(Task task) {
        printLine();
        System.out.println("OK, I've marked this task as not done yet:");
        task.markAsNotDone();
        System.out.println(task.toString());
        printLine();
    }

    public static void addTask(Task task) {
        tasks[count] = task;
        count++;
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.printf("Now you have %d tasks in the list.\n", count);
        printLine();
    }

}
