import java.util.Scanner;

public class Junny {
    public static void main(String[] args) {
        String[] list_of_act = new String[100];
        int count = 0;
        Scanner scanner = new Scanner(System.in);
        printLine();
        System.out.println(" Hello! I'm Junny");
        System.out.println(" What can I do for you?");
        printLine();

        while (true) {
            String user_input = scanner.nextLine();
            if (user_input.equals("bye")) {
                System.out.println(" Bye. Hope to see you again soon!");
                printLine();
                break;
            } else if (user_input.equals("list")) {
                printLine();
                for (int i = 0; i < count; i++) {
                    System.out.println((i + 1) + "." + list_of_act[i]);
                }
                printLine();
            } else {
                list_of_act[count] = user_input;
                count++;
                printLine();
                System.out.println(" added: " + user_input);
                printLine();
            }
        }
        scanner.close();
    }

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }
}
