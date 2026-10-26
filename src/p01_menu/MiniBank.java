// [Requirement 1] Set up environment & private repo oop-minibank-25CE090
import java.util.Scanner;

// [Requirement 3] Record BankInfo with name and branch (auto-generates constructor and accessors)
record BankInfo(String name, String branch) {}

// [Requirement 4] Enum MenuOption with constants OPEN_ACCOUNT, DEPOSIT, WITHDRAW, TRANSFER, EXIT
enum MenuOption {
    OPEN_ACCOUNT,
    DEPOSIT,
    WITHDRAW,
    TRANSFER,
    EXIT
}

// [Requirement 2] Public class MiniBank with main method
public class MiniBank {
    public static void main(String[] args) {
        // [Requirement 3] Print BankInfo object as application header
        BankInfo bankInfo = new BankInfo("MiniBank CHARUSAT", "Changa Central");
        System.out.println("=== Bank Header ===");
        System.out.println(bankInfo);

        Scanner scanner = new Scanner(System.in);
        MenuOption choice = null;

        // [Requirement 6] Loop showing menu until EXIT
        while (choice != MenuOption.EXIT) {
            // [Requirement 5] Display numbered menu to user
            System.out.println("\n--- MiniBank Menu ---");
            MenuOption[] options = MenuOption.values();
            for (int i = 0; i < options.length; i++) {
                System.out.println((i + 1) + ". " + options[i]);
            }

            // [Requirement 5] Read chosen number with Scanner
            System.out.print("Enter choice: ");
            int optionNum = scanner.nextInt();

            if (optionNum < 1 || optionNum > options.length) {
                System.out.println("Invalid selection! Please enter 1 to 5.");
                continue;
            }

            choice = options[optionNum - 1];

            // [Requirement 6] Switch expression on choice to print placeholder
            String message = switch (choice) {
                case OPEN_ACCOUNT -> "Open Account -- to be implemented in a later lab";
                case DEPOSIT -> "Deposit -- to be implemented in a later lab";
                case WITHDRAW -> "Withdraw -- to be implemented in a later lab";
                case TRANSFER -> "Transfer -- to be implemented in a later lab";
                case EXIT -> "Thank you for choosing MiniBank. Goodbye!";
            };

            System.out.println("Placeholder: " + message);
        }
        scanner.close();
    }
}