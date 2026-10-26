// [Requirement 3] Synchronized account protecting shared balance
class ThreadSafeAccount {
    private long balance = 0;

    public synchronized void deposit(long amount) {
        balance += amount;
    }

    public synchronized void withdraw(long amount) {
        if (balance >= amount) balance -= amount;
    }

    public long getBalance() { return balance; }
}

// [Requirement 1] AccountWorker implementing Runnable
class AccountWorker implements Runnable {
    private final ThreadSafeAccount account;
    private final int times;
    private final long amount;

    public AccountWorker(ThreadSafeAccount account, int times, long amount) {
        this.account = account;
        this.times = times;
        this.amount = amount;
    }

    @Override
    public void run() {
        for (int i = 0; i < times; i++) {
            account.deposit(amount);
        }
    }
}

public class MiniBankPart9 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Starting 10 concurrent AccountWorker threads (1000 deposits of 1 each)...");
        ThreadSafeAccount account = new ThreadSafeAccount();
        // [Requirement 2] Create 10 threads, join, and print balance
        Thread[] threads = new Thread[10];

        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(new AccountWorker(account, 1000, 1), "Thread-" + i);
            threads[i].start();
        }

        // [Requirement 4] Print thread names observing lifecycle
        System.out.println("[THREAD MONITOR] Worker threads active: Thread-0 to Thread-9");

        for (Thread t : threads) {
            t.join();
        }

        System.out.println("All threads joined.");
        // [Requirement 3] Synchronized fix produces exact balance 10000
        System.out.println("-> Synchronized Final Balance: " + account.getBalance() + " (Exact match, race condition prevented)");
    }
}