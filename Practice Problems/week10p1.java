
import java.util.*;

public class week10p1 {
    static abstract class Account {
        String id;
        int balance;

        Account(String id, int balance) {
            this.id = id;
            this.balance = balance;
        }

        abstract boolean canWithdraw(int amount);

        abstract String rejectionReason();

        void withdraw(int amount) {
            if (amount <= 0) {
                System.out.println(id + " rejected: invalid amount");
            } else if (!canWithdraw(amount)) {
                System.out.println(id + " rejected: " + rejectionReason());
            } else {
                balance -= amount;
                System.out.println(id + " balance " + balance);
            }
        }
    }

    static class Savings extends Account {
        Savings(String id, int balance) {
            super(id, balance);
        }

        boolean canWithdraw(int amount) {
            return balance - amount >= 1000;
        }

        String rejectionReason() {
            return "minimum balance 1000";
        }
    }

    static class Current extends Account {
        Current(String id, int balance) {
            super(id, balance);
        }

        boolean canWithdraw(int amount) {
            return balance - amount >= -5000;
        }

        String rejectionReason() {
            return "overdraft limit 5000";
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Account> accounts = new HashMap<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] parts = line.split("\\s+");

            if (parts[0].equals("Savings") ||
                parts[0].equals("Current")) {
                String id = parts[1];
                int balance = Integer.parseInt(parts[2]);

                if (parts[0].equals("Savings")) {
                    accounts.put(id, new Savings(id, balance));
                } else {
                    accounts.put(id, new Current(id, balance));
                }
            } else if (parts[0].equals("WITHDRAW")) {
                String id = parts[1];
                int amount = Integer.parseInt(parts[2]);

                if (!accounts.containsKey(id)) {
                    System.out.println("Account not found");
                } else {
                    accounts.get(id).withdraw(amount);
                }
            }
        }

        sc.close();
    }
}
