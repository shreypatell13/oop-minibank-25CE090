import java.util.regex.Pattern;

// [Requirement 1] Validator class with compiled regex Patterns
class Validator {
    private static final Pattern MOBILE_PATTERN = Pattern.compile("^[6-9]\\d{9}$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$");
    private static final Pattern PAN_PATTERN = Pattern.compile("^[A-Z]{5}[0-9]{4}[A-Z]$");
    private static final Pattern IFSC_PATTERN = Pattern.compile("^[A-Z]{4}0[A-Z0-9]{6}$");

    public static boolean isValidMobile(String s) { return s != null && MOBILE_PATTERN.matcher(s).matches(); }
    public static boolean isValidEmail(String s) { return s != null && EMAIL_PATTERN.matcher(s).matches(); }
    public static boolean isValidPan(String s) { return s != null && PAN_PATTERN.matcher(s).matches(); }
    public static boolean isValidIfsc(String s) { return s != null && IFSC_PATTERN.matcher(s).matches(); }
}

// [Requirement 2] Enum TransactionType and Record Command
enum TransactionType { DEPOSIT, WITHDRAW, TRANSFER }

record Command(TransactionType type, String accountNumber, long amount) {}

// [Requirement 3] CommandParser splitting line and returning Command
class CommandParser {
    public static Command parse(String line) {
        String[] parts = line.trim().split("\\s+");
        TransactionType type = TransactionType.valueOf(parts[0].toUpperCase());
        String acc = parts[1];
        long amt = Long.parseLong(parts[2]);
        return new Command(type, acc, amt);
    }
}

// [Requirement 4] StatementFormatter assembling statement with StringBuilder
class StatementFormatter {
    public static String buildStatement(String accNum, String owner, long balance) {
        StringBuilder sb = new StringBuilder();
        sb.append("----------------------------------------\n");
        sb.append("STATEMENT FOR ACCOUNT: ").append(accNum).append("\n");
        sb.append("Owner: ").append(owner).append(" | Current Balance: ").append(balance).append("\n");
        sb.append("----------------------------------------");
        return sb.toString();
    }
}

public class MiniBankPart4 {
    public static void main(String[] args) {
        // [Requirement 5] Test each validator with correct and wrong input
        System.out.println("--- Running Validation Layer Tests ---");
        System.out.println("isValidMobile(9876543210): " + Validator.isValidMobile("9876543210"));
        System.out.println("isValidMobile(12345): " + Validator.isValidMobile("12345"));
        System.out.println("isValidEmail(shrey@charusat.edu): " + Validator.isValidEmail("shrey@charusat.edu"));
        System.out.println("isValidEmail(bad_email): " + Validator.isValidEmail("bad_email"));
        System.out.println("isValidPan(ABCDE1234F): " + Validator.isValidPan("ABCDE1234F"));
        System.out.println("isValidIfsc(SBIN0001234): " + Validator.isValidIfsc("SBIN0001234"));

        // [Requirement 5] Parse sample command and print three parts
        System.out.println("\n--- Parsing Command Line Input ---");
        Command cmd = CommandParser.parse("DEPOSIT AC0001 500");
        System.out.println("Command parsed: " + cmd);

        String statement = StatementFormatter.buildStatement(cmd.accountNumber(), "Shrey Patel", cmd.amount());
        System.out.println("Statement Output:\n" + statement);
    }
}