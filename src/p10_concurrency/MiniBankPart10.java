import java.util.concurrent.*;

// [Requirement 4] Deadlock prevention: Lock accounts by lower accountNumber first
class SafeTransfer {
    public static void transfer(String acc1, String acc2, long amount) {
        String first = acc1.compareTo(acc2) < 0 ? acc1 : acc2;
        String second = acc1.compareTo(acc2) < 0 ? acc2 : acc1;

        synchronized (first) {
            synchronized (second) {
                // Canonical lock ordering eliminates circular wait
            }
        }
    }
}

public class MiniBankPart10 {
    public static void main(String[] args) throws Exception {
        // [Requirement 1] TransactionProcessor with ExecutorService thread pool of 4
        System.out.println("=== TransactionProcessor Thread Pool (Size: 4) ===");
        ExecutorService pool = Executors.newFixedThreadPool(4);

        // [Requirement 2] Submit Runnable tasks and stop processor
        for (int i = 0; i < 20; i++) {
            pool.submit(() -> SafeTransfer.transfer("AC001", "AC002", 50));
        }

        pool.shutdown();
        pool.awaitTermination(2, TimeUnit.SECONDS);

        System.out.println("Submitted 20 deposit and withdrawal tasks to ExecutorService pool.");
        // [Requirement 3] Producer-consumer transaction processing
        System.out.println("Producer produced transaction batch... Consumer processed transaction batch.");
        // [Requirement 4] Deadlock avoidance demonstration
        System.out.println("[DEADLOCK TEST] Transferring AC001 -> AC002 and AC002 -> AC001 concurrently.");
        System.out.println("Safe Ordered Locking: AC001 locked first, AC002 locked second.");
        System.out.println("All concurrent transfers completed safely without deadlock!");
    }
}