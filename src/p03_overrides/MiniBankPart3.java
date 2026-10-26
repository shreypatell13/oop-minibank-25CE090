import java.util.Objects;

class Account3 {
    private final String accountNumber;
    private final String ownerName;
    private final long balance;
    private final boolean active;

    public Account3(String accountNumber, String ownerName, long balance, boolean active) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = balance;
        this.active = active;
    }

    // [Requirement 1] Override toString() to return readable line
    @Override
    public String toString() {
        return String.format("%s | %s | Balance: %d | Active: %b", accountNumber, ownerName, balance, active);
    }

    // [Requirement 2] Override equals() comparing accountNumber
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account3 a)) return false;
        return Objects.equals(accountNumber, a.accountNumber);
    }

    // [Requirement 2] Override hashCode() matching equals
    @Override
    public int hashCode() {
        return Objects.hash(accountNumber);
    }
}

// [Requirement 4] Implement Cloneable and add clone() method
class Customer3 implements Cloneable {
    private String name;
    private Address address; // [Requirement 3] Address field in Customer

    // [Requirement 3] Public static nested class Address
    public static class Address {
        private final String line;
        private final String city;
        private final String pincode;

        public Address(String line, String city, String pincode) {
            this.line = line;
            this.city = city;
            this.pincode = pincode;
        }

        public String getLine() { return line; }
        public String getCity() { return city; }
        public String getPincode() { return pincode; }

        @Override
        public String toString() { return line + ", " + city + ", " + pincode; }
    }

    public Customer3(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    // [Requirement 4] clone() method returning copy of customer
    @Override
    public Customer3 clone() {
        try {
            return (Customer3) super.clone();
        } catch (CloneNotSupportedException e) {
            return new Customer3(this.name, this.address);
        }
    }

    public String getName() { return name; }
    public Address getAddress() { return address; }
}

public class MiniBankPart3 {
    public static void main(String[] args) {
        // [Requirement 5] Print accounts using toString() and compare with equals()
        Account3 a1 = new Account3("AC0001", "Shrey Patel", 4000, true);
        Account3 a2 = new Account3("AC0001", "Shrey Duplicate", 9500, true);

        System.out.println("Account 1: " + a1);
        System.out.println("Account 2: " + a2);
        System.out.println("Equality check a1.equals(a2): " + a1.equals(a2));

        // [Requirement 3 & 4] Nested Address and Customer clone test
        Customer3.Address addr = new Customer3.Address("Station Road", "Anand", "388001");
        Customer3 c1 = new Customer3("Shrey Patel", addr);
        Customer3 cloned = c1.clone();

        System.out.println("Customer Address: " + c1.getAddress());
        System.out.println("Cloned Customer: " + cloned.getName() + " (Copy generated successfully)");

        // [Requirement 5] Use instanceof to check object type
        System.out.println("instanceof Check: a1 is instance of Account: " + (a1 instanceof Account3));
    }
}