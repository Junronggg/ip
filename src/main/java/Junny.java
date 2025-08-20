import java.util.Scanner;

public class Junny {
    public static void main(String[] args) {
        Task[] tasks = new Task[100];
        int count = 0;
        Scanner scanner = new Scanner(System.in);
        printLine();
        System.out.println(" Hello! I'm Junny");
        System.out.println(" What can I do for you?");
        printLine();

        while (true) {
            String userInput = scanner.nextLine();
            String[] inputByParts = userInput.split(" ");
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
            }
            else {
                Task t = new Task(userInput);
                tasks[count] = t;
                count++;
                printLine();
                System.out.println(" added: " + userInput);
                printLine();
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
}
