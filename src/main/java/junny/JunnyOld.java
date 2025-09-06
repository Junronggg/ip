package junny;

import java.time.LocalDate;
import java.util.ArrayList;

import junny.Ui.Ui;
import junny.tasks.Deadline;
import junny.tasks.Event;
import junny.tasks.Task;
import junny.tasks.Todo;

public class JunnyOld {
    // make it global, so all mthds can access and change
    // the fields need to be static, so can be accessed in the main mthd

    // when interact with Junny.Junny, each time there is a 'interactive board', which is the object of Junny.Ui class
    private static Ui ui = new Ui();
    // for parsing commands
    // static Junny.Parser parser = new Junny.Parser(ui);
    // for file storage
    private static Storage storage = new Storage("./data/Junny.Junny.txt");
    private static ArrayList<Task> tasks = storage.loadAllTasks();
    private static int count = tasks.size();

    // exceptions to be handled:
    // 1. todo, deadline and event cannot be empty
    // 2. cannot give any other commands other than todo, deadline, event, list, mark, unmark
    // 3. mark must be followed by number < list.length, must be valid int number
    // 4. unmark must be a task that is marked, must be followed by number < list.length
    // 5. deadline must follow /by
    // 6. event must follow /from /to

    public static void main(String[] args) {
        ui.printHi();

        // all inputs: todo, deadline, event, list, mark, unmark, list on
        while (true) {
            String userInput = ui.readCommands();

            // handle the input
            // split to "ddl" and "read book \by Sunday" OR "mark" and "2"
            String[] inputByParts = userInput.split(" ", 2);
            String commandWord = inputByParts[0].toUpperCase();
            CommandTypes command;

            try {
                command = CommandTypes.valueOf(commandWord);
            } catch (IllegalArgumentException e) {
                // handle exception 2
                ui.printError("I'm sorry, but I don't know what that means :(");
                continue;
            }

            if (command == CommandTypes.BYE) {
                ui.printBye();
                break;
            } else if (command == CommandTypes.LIST) {
                // case when the user said "list": just print the whole list of events
                if (inputByParts.length == 1) {
                    printAllTasks(tasks, count);
                } else {
                    // case when the user said "list /on dd": print only task on that date
                    if (inputByParts[1].startsWith("/on ")) {
                        String dateStr = inputByParts[1].substring(4).trim();
                        // get the date string, which is 4th position
                        try {
                            LocalDate targetDate = LocalDate.parse(dateStr); // yyyy-MM-dd
                            printTaskOnDate(targetDate);
                        } catch (Exception e) {
                            ui.printError("Please enter the date in yyyy-MM-dd format.");
                        }
                    } else {
                        ui.printError("Invalid list command. Use 'list' or 'list /on yyyy-MM-dd'");
                    }
                }
            } else if (command == CommandTypes.FIND) {
                String searchWord = inputByParts[1];
                printLine();
                boolean hasMatchingTask = false;
                // ArrayList<Task> result = new ArrayList<>();
                for (Task t : tasks) {
                    if (t.toString().contains(searchWord)) {
                        System.out.println(t.toString());
                        hasMatchingTask = true;
                    }
                }
                if (!hasMatchingTask) {
                    System.out.println("There is no matching task :(");
                }
                printLine();
            } else if (command == CommandTypes.MARK || command == CommandTypes.UNMARK
                    || command == CommandTypes.DELETE) {
                try {
                    // VERY IMPORTANT: mark 2 extract 2, but it actually mark tasks[1]!!!
                    int index = Integer.parseInt(inputByParts[1]) - 1; // may throw NumberFormatException
                    // throw exception 3: may throw ArrayIndexOutOFBoundsException
                    if (command == CommandTypes.MARK) {
                        markDone(tasks.get(index));
                    } else if (command == CommandTypes.UNMARK) {
                        if (tasks.get(index).isDone()) {
                            markUndone(tasks.get(index));
                        } else {
                            // throw & handle exception 4
                            ui.printError("The task is not done yet, and you do not need to undo it!");
                        }
                    } else if (command == CommandTypes.DELETE) {
                        deleteTask(tasks.get(index));
                    }
                } catch (NumberFormatException e) {
                    // handle exception 3
                    ui.printError("Please enter a valid number for " + command + ".");
                } catch (IndexOutOfBoundsException | NullPointerException e) {
                    // handle exception 3
                    ui.printError("The task number you give does not exist. Please check again!");
                }
            } else if (command == CommandTypes.DEADLINE || command == CommandTypes.TODO
                    || command == CommandTypes.EVENT) {
                // if is todo, deadline, event: handle input first
                try {
                    String details = inputByParts[1];
                    if (command == CommandTypes.DEADLINE) {
                        // split to "read book" & "Sunday"
                        String[] parts = details.split("/by", 2);
                        // throw exception 5
                        if (parts.length < 2) {
                            throw new IllegalArgumentException("deadline task must have a due time. "
                                    + "Please follow deadline read /by xx.");
                        }
                        String description = parts[0].trim(); // "read book"
                        String by = parts[1].trim(); // "Sunday
                        Deadline ddl = new Deadline(description, by);
                        addTask(ddl);
                    } else if (command == CommandTypes.EVENT) {
                        // split to "read book" & "/from xxx" (split on from)
                        String[] parts1 = details.split("/from", 2);
                        // throw exception 6
                        if (parts1.length < 2) {
                            throw new IllegalArgumentException("event task must have a from time. "
                                    + "Please follow event read /from xx /to yy.");
                        }
                        String description = parts1[0].trim(); // "read book"
                        String fromTo = parts1[1].trim(); // "/from xxx /to xxx"
                        // split to "from" & "to"
                        String[] parts2 = fromTo.split("/to", 2);
                        if (parts2.length < 2) {
                            throw new IllegalArgumentException("event task must have a to time. "
                                    + "Please follow event read /from xx /to yy.");
                        }

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
                    ui.printError("The task description cannot be exmpty!");
                } catch (IllegalArgumentException e) {
                    // handle exception 5 & 6
                    ui.printError(e.getMessage());
                }
            } else {
                // handle exception 2
                ui.printError("I'm sorry, but I don't know what that means :(");
            }
        }
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

    public static void markDone(Task task) {
        printLine();
        System.out.println("Nice! I've marked this task as done:");
        task.markAsDone();
        System.out.println(task.toString());
        printLine();
        storage.saveAllTasks(tasks);
    }

    public static void markUndone(Task task) {
        printLine();
        System.out.println("OK, I've marked this task as not done yet:");
        task.markAsNotDone();
        System.out.println(task.toString());
        printLine();
        storage.saveAllTasks(tasks);
    }

    public static void addTask(Task task) {
        tasks.add(task);
        count++;
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.printf("Now you have %d tasks in the list.\n", count);
        printLine();
        storage.saveAllTasks(tasks); // save after adding
    }

    public static void deleteTask(Task task) {
        tasks.remove(task);
        printLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        count--;
        System.out.printf("Now you have %d tasks in the list.\n", count);
        printLine();
        storage.saveAllTasks(tasks);
    }

    public static void printTaskOnDate(LocalDate date) {
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
}
