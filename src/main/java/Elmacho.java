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
        int n = 0;
        String[] arrayA = new String[100];
        while (true) {
            String instruction = scanner.nextLine();
            if (Objects.equals(instruction, "list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < n; i++) {
                    System.out.println(i + 1 + ". " + arrayA[i]);
                }
                System.out.println("____________________________________________________________");
            }
            else if (Objects.equals(instruction, "blah")) {
                System.out.println("____________________________________________________________");
                System.out.println("blahblahblahblahblah");
                System.out.println("____________________________________________________________");
            }
            else if (Objects.equals(instruction, "bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("OkBye.");
                System.out.println("____________________________________________________________");
                break;
            }
            else {
                arrayA[n] = instruction;
                n = n + 1;
                System.out.println("____________________________________________________________");
                System.out.println("added: " + instruction);
                System.out.println("____________________________________________________________");
            }
        }
    }
}
