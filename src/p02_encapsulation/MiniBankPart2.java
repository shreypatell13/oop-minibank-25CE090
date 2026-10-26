// [Requirement 1] Customer class with encapsulated fields and static ID generator
class Customer {
    private static long customerCounter = 101;
    private final String customerId;
    private String name;
    private String email;
    private String mobile;

    public Customer(String name, String email, String mobile) {
        this.customerId = generateCustomerId();
        this.name = name;
        this.email = email;
        this.mobile = mobile;
    }

    private static String generateCustomerId() {
        return "CUST" + (customerCounter++);
    }

    public String getCustomerId() { return customerId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getMobile() { return mobile; }
}

// [Requirement 2] Account class with private fields and static account counter
class Account {
    private static long accountCounter = 1;
    private final String accountNumber;
    private String ownerName;
    private long balance;
    private boolean active;

    // [Requirement 3] Constructor taking ownerName and opening balance
    public Account(String ownerName, long balance) {
        this.accountNumber = String.format("AC%04d", accountCounter++);
        this.ownerName = ownerName;
        this.balance = balance;
        this.active = true;
    }

    // [Requirement 3] Constructor chaining using this(ownerName, 0)
    public Account(String ownerName) {
        this(ownerName, 0);
    }

    // [Requirement 4] deposit adds amount to balance
    public void deposit(long amount) {
        if (amount > 0) balance += amount;
    }

    // [Requirement 4] withdraw returns true if funds sufficient, else false
    public boolean withdraw(long amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    // Getters provided, no public setter for balance
    public String getAccountNumber() { return accountNumber; }
    public String getOwnerName() { return ownerName; }
    public long getBalance() { return balance; }
    public boolean isActive() { return active; }
}

public class MiniBankPart2 {
    public static void main(String[] args) {
        // [Requirement 1] Verify Customer registration
        Customer c1 = new Customer("Shrey Patel", "shrey@example.com", "9876543210");
        System.out.printf("Customer Registered: %s | %s | %s | %s%n",
                c1.getCustomerId(), c1.getName(), c1.getEmail(), c1.getMobile());

        // [Requirement 5] Create three Account objects in an Account[] array
        Account[] accounts = new Account[3];
        accounts[0] = new Account("Shrey Patel", 5000);
        accounts[1] = new Account("Corporate Account", 20000);
        accounts[2] = new Account("Savings Primary");

        // [Requirement 5] Perform deposits, withdrawals, and print each balance
        accounts[0].deposit(2000);
        boolean w1 = accounts[0].withdraw(3000);
        boolean w2 = accounts[0].withdraw(10000); // Fails (insufficient)

        for (Account acc : accounts) {
            System.out.printf("Account: %s | Owner: %-18s | Balance: %d | Active: %b%n",
                    acc.getAccountNumber(), acc.getOwnerName(), acc.getBalance(), acc.isActive());
        }
        System.out.println("Withdraw 3000 success: " + w1 + " | Withdraw 10000 success: " + w2);
    }
}