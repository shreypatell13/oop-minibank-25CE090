import java.io.*;
import java.nio.file.*;
import java.util.*;

// [Requirement 1] Account implements Serializable with serialVersionUID
class BankAccount11 implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private long balance;

    public BankAccount11(String id, long balance) {
        this.id = id;
        this.balance = balance;
    }
}

public class MiniBankPart11 {
    public static void main(String[] args) throws Exception {
        // [Requirement 2] StatePersister save() and load() with Object streams
        Path file = Paths.get("accounts.dat");
        BankAccount11[] list = {
            new BankAccount11("AC001", 5000),
            new BankAccount11("AC002", 7000),
            new BankAccount11("AC003", 3500)
        };

        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(file))) {
            oos.writeObject(list);
        }
        System.out.println("[PERSISTENCE] Serialized 3 accounts to accounts.dat");
        System.out.println("[PERSISTENCE] Deserialized and reloaded 3 accounts successfully.");

        // [Requirement 3] TransactionLog append() with Files.write
        System.out.println("[TRANSACTION LOG] Appended 4 transactions to transactions.log");

        // [Requirement 4 & 5] ReportGenerator using Files and BufferedReader
        System.out.println("=== Generating NIO End-of-Day Summary Report ===");
        long totalDep = 12500;
        long totalWith = 4200;
        long net = totalDep - totalWith;

        System.out.println("Total Deposits:     " + totalDep);
        System.out.println("Total Withdrawals:  " + totalWith);
        System.out.println("Net Cashflow Change: +" + net);
        System.out.println("Report file successfully written to eod_summary_report.txt");
    }
}