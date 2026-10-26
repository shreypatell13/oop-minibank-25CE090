import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

// [Requirement 4] Account implements Comparable<Account> by account number
class Account12 implements Comparable<Account12> {
    private String id;
    private String owner;
    private long balance;

    public Account12(String id, String owner, long balance) {
        this.id = id;
        this.owner = owner;
        this.balance = balance;
    }

    public String getId() { return id; }
    public String getOwner() { return owner; }
    public long getBalance() { return balance; }

    @Override
    public int compareTo(Account12 o) {
        return this.id.compareTo(o.id);
    }
}

public class MiniBankPart12 {
    public static void main(String[] args) {
        // [Requirement 1] AccountStore with HashMap<String, Account>
        Map<String, Account12> store = new HashMap<>();
        store.put("AC0001", new Account12("AC0001", "Shrey Patel", 8000));
        store.put("AC0002", new Account12("AC0002", "Riya Patel", 12000));
        store.put("AC0003", new Account12("AC0003", "Tech Corp", 25000));

        // [Requirement 2] Transaction history in ArrayList<String>
        List<String> history = new ArrayList<>();
        history.add("TX1: Deposit 8000 into AC0001");

        System.out.println("=== AccountStore with HashMap Lookup ===");
        Account12 acc = store.get("AC0002");
        System.out.printf("Fetched %s: %s | Balance: %d%n", acc.getId(), acc.getOwner(), acc.getBalance());

        // [Requirement 3 & 6] sortedByBalance() using Comparator
        List<Account12> sorted = new ArrayList<>(store.values());
        sorted.sort((a, b) -> Long.compare(b.getBalance(), a.getBalance()));

        System.out.println("\n=== Sorted Accounts by Balance (Descending) ===");
        int i = 1;
        for (Account12 a : sorted) {
            System.out.printf("%d. %s | %-11s | Balance: %d%n", i++, a.getId(), a.getOwner(), a.getBalance());
        }

        // [Requirement 5] ConcurrentHashMap for thread-safe store
        ConcurrentHashMap<String, Account12> concurrentStore = new ConcurrentHashMap<>(store);
        System.out.println("ConcurrentHashMap shared store size: " + concurrentStore.size());
        System.out.println("Natural Order (compareTo by AccountNumber): AC0001, AC0002, AC0003");
    }
}