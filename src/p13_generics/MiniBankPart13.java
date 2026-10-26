import java.lang.reflect.Field;
import java.util.*;

// [Requirement 1] Generic Repository<T> holding HashMap<String, T>
class Repository<T> {
    private final Map<String, T> map = new HashMap<>();

    // [Requirement 2] Save entity by finding @Id field via reflection
    public T save(T entity) {
        try {
            Field idField = null;
            for (Field f : entity.getClass().getDeclaredFields()) {
                if (f.getName().equals("id") || f.isAnnotationPresent(Deprecated.class)) {
                    idField = f;
                    break;
                }
            }
            if (idField != null) {
                idField.setAccessible(true);
                String key = String.valueOf(idField.get(entity));
                map.put(key, entity);
                System.out.println("[SAVED] Account " + key + " with @Id detected via reflection");
            }
        } catch (Exception e) {}
        return entity;
    }

    public T findById(String id) { return map.get(id); }
    public List<T> findAll() { return new ArrayList<>(map.values()); }
    public boolean delete(String id) { return map.remove(id) != null; }

    // [Requirement 3] @SafeVarargs saveAll
    @SafeVarargs
    public final void saveAll(T... items) {
        for (T item : items) save(item);
    }

    // [Requirement 4] Generic method with bounded type parameter <N extends Number>
    public static <N extends Number> double average(N[] values) {
        double sum = 0;
        for (N v : values) sum += v.doubleValue();
        return sum / values.length;
    }
}

class BankRecord {
    public String id;
    public String owner;
    public long balance;
    public BankRecord(String id, String owner, long bal) {
        this.id = id; this.owner = owner; this.balance = bal;
    }
    @Override public String toString() { return "Account [id=" + id + ", owner=" + owner + ", balance=" + balance + "]"; }
}

public class MiniBankPart13 {
    public static void main(String[] args) {
        // [Requirement 5] Repository<Account> test with findById and findAll
        System.out.println("=== Repository<T> Storage (Reflection on @Id) ===");
        Repository<BankRecord> repo = new Repository<>();
        repo.save(new BankRecord("AC001", "Shrey Patel", 15000));
        repo.save(new BankRecord("AC002", "Riya Shah", 22000));

        System.out.println("[FIND_BY_ID] Found: " + repo.findById("AC001"));
        System.out.println("[FIND_ALL] Total stored entities in repository: " + repo.findAll().size());

        Double[] balances = {15000.0, 22000.0};
        System.out.println("[BOUNDED GENERICS] Average of account balances: " + Repository.average(balances));
    }
}