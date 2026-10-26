// [Requirement 1] Checked base BankException and specific subclasses
class BankException extends Exception {
    public BankException(String message) { super(message); }
}

class InsufficientFundsException extends BankException {
    private final long shortfall; // [Requirement 1] shortfall field
    public InsufficientFundsException(long shortfall) {
        super("Insufficient funds! Shortfall amount: " + shortfall);
        this.shortfall = shortfall;
    }
    public long getShortfall() { return shortfall; }
}

class AccountNotFoundException extends BankException {
    public AccountNotFoundException(String id) { super("Account not found: " + id); }
}

class InvalidAmountException extends BankException {
    public InvalidAmountException(String msg) { super(msg); }
}

// [Requirement 4] Custom AutoCloseable resource for try-with-resources
class AutoAuditLog implements AutoCloseable {
    public AutoAuditLog() { System.out.println("[AUDIT]: Session initialized."); }
    public void record(String action) { System.out.println("[AUDIT]: " + action); }
    @Override public void close() {
        System.out.println("[AUTO-CLOSE]: AuditSession closed automatically via try-with-resources.");
    }
}

public class MiniBankPart8 {
    // [Requirement 2] declare throws InsufficientFundsException, InvalidAmountException
    public static void withdraw(long balance, long amount) throws InsufficientFundsException, InvalidAmountException {
        if (amount <= 0) throw new InvalidAmountException("Withdrawal amount must be positive!");
        if (amount > balance) throw new InsufficientFundsException(amount - balance);
    }

    // [Requirement 3] transfer using try, catch, finally and re-throwing BankException
    public static void transfer(long fromBal, long amount) throws BankException {
        try {
            withdraw(fromBal, amount);
        } catch (InsufficientFundsException | InvalidAmountException e) {
            throw new BankException("Transfer failed: " + e.getMessage());
        } finally {
            System.out.println("[FINALLY]: Transaction checkpoint executed.");
        }
    }

    public static void main(String[] args) {
        System.out.println("Attempting withdrawal of 5000 from balance 1000...");
        // [Requirement 4] try-with-resources and try-catch handling
        try (AutoAuditLog audit = new AutoAuditLog()) {
            audit.record("Initiating withdrawal check...");
            try {
                withdraw(1000, 5000);
            } catch (InsufficientFundsException e) {
                System.out.println("[CAUGHT BankException]: " + e.getMessage());
            } catch (InvalidAmountException e) {
                System.out.println("[CAUGHT InvalidAmountException]: " + e.getMessage());
            } finally {
                System.out.println("[FINALLY]: Operation transaction log finalized.");
            }
        }
    }
}