// [Requirement 1] Abstract Account class with abstract methods
abstract class BaseAccount {
    protected String accountNumber;
    protected String ownerName;
    protected long balance;

    public BaseAccount(String accountNumber, String ownerName, long balance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = balance;
    }

    public abstract double interestRate();
    public abstract boolean canWithdraw(long amount);

    public String getAccountNumber() { return accountNumber; }
    public String getOwnerName() { return ownerName; }
    public long getBalance() { return balance; }
}

// [Requirement 2] SavingsAccount subclass with minBalance and 4.0% interest
class SavingsAccount extends BaseAccount {
    private long minBalance;

    public SavingsAccount(String accNum, String owner, long bal, long minBal) {
        super(accNum, owner, bal); // [Requirement 5] Call super(...)
        this.minBalance = minBal;
    }

    @Override public double interestRate() { return 4.0; }
    @Override public boolean canWithdraw(long amount) { return (balance - amount) >= minBalance; }
}

// [Requirement 3] CurrentAccount subclass with overdraftLimit and 0.0% interest
class CurrentAccount extends BaseAccount {
    private long overdraftLimit;

    public CurrentAccount(String accNum, String owner, long bal, long overdraft) {
        super(accNum, owner, bal); // [Requirement 5] Call super(...)
        this.overdraftLimit = overdraft;
    }

    @Override public double interestRate() { return 0.0; }
    @Override public boolean canWithdraw(long amount) { return (balance - amount) >= -overdraftLimit; }
}

// [Requirement 4] FixedDepositAccount subclass with 7.0% interest (locked withdrawal)
class FixedDepositAccount extends BaseAccount {
    public FixedDepositAccount(String accNum, String owner, long bal) {
        super(accNum, owner, bal); // [Requirement 5] Call super(...)
    }

    @Override public double interestRate() { return 7.0; }
    @Override public boolean canWithdraw(long amount) { return false; }
}

public class MiniBankPart5 {
    public static void main(String[] args) {
        // [Requirement 6] Account[] array demonstrating run-time polymorphism
        System.out.println("=== Processing Polymorphic Account Array ===");
        BaseAccount[] accounts = {
            new SavingsAccount("AC101", "Shrey Patel", 5000, 1000),
            new CurrentAccount("AC102", "Tech Corp", 8000, 5000),
            new FixedDepositAccount("AC103", "Long-Term", 20000)
        };

        for (BaseAccount a : accounts) {
            System.out.printf("[%s] %s (%s) -> Interest Rate: %.1f%% | Can withdraw: %b%n",
                    a.getClass().getSimpleName(), a.getAccountNumber(), a.getOwnerName(),
                    a.interestRate(), a.canWithdraw(4500));

            // [Requirement 6] Pattern matching instanceof
            if (a instanceof FixedDepositAccount fd) {
                System.out.println("Special Handling: FixedDepositAccount identified via pattern matching instanceof!");
            }
        }
    }
}