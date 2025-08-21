import java.util.ArrayList;
import java.util.Scanner;

public class Junny {
    // make it global, so all mthds can access and change
    static ArrayList<Task> tasks = new ArrayList<>();
    static int count = 0;

    // exceptions to be handled:
    // 1. todo, deadline and event cannot be empty
    // 2. cannot give any other commands other than todo, deadline, event, list, mark, unmark
    // 3. mark must be followed by number < list.length, must be valid int number
    // 4. unmark must be a task that is marked, must be followed by number < list.length
    // 5. deadline must follow /by
    // 6. event must follow /from /to

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
            String commandWord = inputByParts[0].toUpperCase();
            CommandTypes command;

            try {
                command = CommandTypes.valueOf(commandWord);
            } catch (IllegalArgumentException e) {
                // handle exception 2
                printError("I'm sorry, but I don't know what that means :(");
                continue;
            }

            if (command == CommandTypes.BYE) {
                printBye();
                break;
            } else if (command == CommandTypes.LIST) {
                printAllTasks(tasks, count);
            } else if (command == CommandTypes.MARK || command == CommandTypes.UNMARK || command == CommandTypes.DELETE) {
                try {
                    // VERY IMPORTANT: mark 2 extract 2, but it actually mark tasks[1]!!!
                    int index = Integer.parseInt(inputByParts[1]) - 1;  // may throw NumberFormatException
                    // throw exception 3: may throw ArrayIndexOutOFBoundsException
                    if (command == CommandTypes.MARK) {
                        markDone(tasks.get(index));
                    } else if (command == CommandTypes.UNMARK) {
                        if (tasks.get(index).isDone) markUndone(tasks.get(index));
                        // throw & handle exception 4
                        else printError("The task is not done yet, and you do not need to undo it!");
                    } else if (command == CommandTypes.DELETE) {
                        deleteTask(tasks.get(index));
                    }
                } catch (NumberFormatException e) {
                    // handle exception 3
                    printError("Please enter a valid number for " + command + ".");
                } catch (IndexOutOfBoundsException | NullPointerException e) {
                    // handle exception 3
                    printError("The task number you give does not exist. Please check again!");
                }
            }
            else if (command == CommandTypes.DEADLINE || command == CommandTypes.TODO || command == CommandTypes.EVENT){
                // if is todo, deadline, event: handle input first
                try{
                    String details = inputByParts[1];
                    if (command == CommandTypes.DEADLINE) {
                       // split to "read book" & "Sunday"
                        String[] parts = details.split("/by", 2);
                        // throw exception 5
                        if (parts.length < 2) throw new IllegalArgumentException("deadline task must have a due time. Please follow deadline read /by xx.");
                        String description = parts[0].trim();   // "read book"
                        String by = parts[1].trim();    // "Sunday
                        Deadline ddl = new Deadline(description, by);
                        addTask(ddl);
                    } else if (command == CommandTypes.EVENT) {
                        // split to "read book" & "/from xxx" (split on from)
                        String[] parts1 = details.split("/from", 2);
                        // throw exception 6
                        if (parts1.length <2) throw new IllegalArgumentException("event task must have a from time. Please follow event read /from xx /to yy.");
                        String description = parts1[0].trim();   // "read book"
                        String fromTo = parts1[1].trim();    // "/from xxx /to xxx"
                        // split to "from" & "to"
                        String[] parts2 = fromTo.split("/to", 2);
                        if (parts2.length <2) throw new IllegalArgumentException("event task must have a to time. Please follow event read /from xx /to yy.");

                        String from = parts2[0].trim();
                        String to = parts2[1].trim();

                        Event event = new Event(description, from, to);
                        addTask(event);
                    } else {
                        Todo todo = new Todo(details);
                        addTask(todo);
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    // handle exception 1: make sure it is not empty
                    printError("The task description cannot be exmpty!");
                } catch (IllegalArgumentException e) {
                    // handle exception 5 & 6
                    printError(e.getMessage());
                }
            } else {
                // handle exception 2
                printError("I'm sorry, but I don't know what that means :(");
            }
        }
        scanner.close();
    }

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public static void printAllTasks(ArrayList<Task> tasks, int count) {
        printLine();
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < count; i++) {
            System.out.println(tasks.get(i).toString());
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
        tasks.add(task);
        count++;
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.printf("Now you have %d tasks in the list.\n", count);
        printLine();
    }

    public static void deleteTask(Task task) {
        tasks.remove(task);
        printLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        count--;
        System.out.printf("Now you have %d tasks in the list.\n", count);
        printLine();
    }

    public static void printError(String msg) {
        printLine();
        System.out.println("OOPS!!!" + msg);
        printLine();
    }
}