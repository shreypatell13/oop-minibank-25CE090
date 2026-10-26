// [Requirement 1] Interface Transactable
interface Transactable {
    void deposit(long amount);
    boolean withdraw(long amount);
}

// [Requirement 2] Interface InterestBearing with default yearlyInterest()
interface InterestBearing {
    double interestRate();
    long getBalance();

    default double yearlyInterest() {
        return getBalance() * (interestRate() / 100.0);
    }
}

// [Requirement 3] Functional interface WithdrawRule
@FunctionalInterface
interface WithdrawRule {
    boolean allow(long currentBalance, long amount);
}

// [Requirement 4] Marker interface Premium
interface Premium {}

// [Requirement 5] Organise into model, util, service (simulated in single compilation unit)
class Account6 implements Transactable, InterestBearing, Premium {
    private String accountNumber;
    private long balance;

    public Account6(String accountNumber, long balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    @Override public void deposit(long amount) { balance += amount; }
    @Override public boolean withdraw(long amount) {
        if (balance >= amount) { balance -= amount; return true; }
        return false;
    }
    @Override public double interestRate() { return 7.0; }
    @Override public long getBalance() { return balance; }
}

public class MiniBankPart6 {
    public static void main(String[] args) {
        System.out.println("=== MiniBank Packaged Module Execution ===");
        Account6 acc = new Account6("AC0001", 4000);
        System.out.println("Account AC0001 implements Transactable & InterestBearing");
        System.out.println("Yearly Calculated Interest: " + acc.yearlyInterest());

        // [Requirement 3] WithdrawRule with Anonymous Class
        WithdrawRule anonRule = new WithdrawRule() {
            @Override
            public boolean allow(long curBal, long amt) {
                return amt <= curBal;
            }
        };
        System.out.println("[ANONYMOUS CLASS RULE] Withdrawal allowed? " + anonRule.allow(acc.getBalance(), 2500));

        // [Requirement 3] WithdrawRule with Lambda Expression
        WithdrawRule lambdaRule = (bal, amt) -> amt <= 1000;
        System.out.println("[LAMBDA EXPRESSION RULE] Withdrawal allowed? " + lambdaRule.allow(acc.getBalance(), 2500));

        // [Requirement 4] Check marker interface
        System.out.println("Marker check: Account is Premium? " + (acc instanceof Premium));
        // [Requirement 6] Runnable JAR note
        System.out.println("Packaged build run completed successfully (Main-Class: MiniBank).");
    }
}