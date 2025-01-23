import java.util.Objects;
import java.util.Scanner;

public class Elmacho {
    public static void main(String[] args) {
        String name = "Elmacho";
        System.out.println("____________________________________________________________");
        System.out.println("What. I'm " + name);
        System.out.println("What you want?");
        System.out.println("____________________________________________________________");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String instruction = scanner.next();
            if (Objects.equals(instruction, "list")) {
                System.out.println("____________________________________________________________");
                System.out.println("here. your list");
                System.out.println("____________________________________________________________");
            }
            if (Objects.equals(instruction, "blah")) {
                System.out.println("____________________________________________________________");
                System.out.println("blahblahblahblahblah");
                System.out.println("____________________________________________________________");
            }
            if (Objects.equals(instruction, "bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("OkBye.");
                System.out.println("____________________________________________________________");
                break;
            }
        }
    }
}
