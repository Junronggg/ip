import java.util.Scanner;

public class Junny {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Junny");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            String user_input = scanner.nextLine();
            if (user_input.equals("bye")) {
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else {
                System.out.println("____________________________________________________________");
                System.out.println(user_input);
                System.out.println("____________________________________________________________");
            }
        }
        scanner.close();
    }
}
