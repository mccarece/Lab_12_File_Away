import java.util.Scanner;

public class SafeInput {
    private static final Scanner scanner = new Scanner(System.in);

    public static String getNonZeroLenString(String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Input cannot be empty. Try again.");
            }
        } while (input.isEmpty());
        return input;
    }

    public static int getRangedInt(String prompt, int min, int max) {
        int value = 0;
        boolean valid = false;
        do {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                value = scanner.nextInt();
                if (value >= min && value <= max) {
                    valid = true;
                } else {
                    System.out.printf("Input must be between %d and %d.%n", min, max);
                }
            } else {
                System.out.println("That's not an integer!");
                scanner.next();
            }
        } while (!valid);
        scanner.nextLine();
        return value;
    }

    public static boolean getYNConfirm(String prompt) {
        String input;
        do {
            System.out.print(prompt + " (Y/N): ");
            input = scanner.nextLine().trim().toUpperCase();
            if (input.equals("Y")) return true;
            if (input.equals("N")) return false;
            System.out.println("Invalid input. Please enter Y or N.");
        } while (true);
    }
}
