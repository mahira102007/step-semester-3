
import java.util.*;

interface PaymentMethod {
    boolean processPayment(double amount);
    String name();
}

class CreditCardPayment implements PaymentMethod {
    public boolean processPayment(double amount) {
        return true;
    }

    public String name() {
        return "Credit Card";
    }
}

class PayPalPayment implements PaymentMethod {
    public boolean processPayment(double amount) {
        return false;
    }

    public String name() {
        return "PayPal";
    }
}

class BankTransferPayment implements PaymentMethod {
    public boolean processPayment(double amount) {
        return true;
    }

    public String name() {
        return "Bank Transfer";
    }
}

class Product {
    final String name;
    final double price;

    Product(String name, double price) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Product name required");

        if (price < 0)
            throw new IllegalArgumentException("Invalid price");

        this.name = name;
        this.price = price;
    }
}

class Order {
    enum Status {
        PENDING, PAID
    }

    private final String customer;
    private final String orderId;
    private final Map<Product, Integer> items = new LinkedHashMap<>();
    private Status status = Status.PENDING;

    Order(String customer, String orderId) {
        this.customer = customer;
        this.orderId = orderId;
        System.out.println("Order created for " + customer + ".");
    }

    void addProduct(Product p, int qty) {
        if (status == Status.PAID)
            throw new IllegalStateException(
                "Cannot modify a paid order"
            );

        if (p == null)
            throw new IllegalArgumentException("Product cannot be null");

        if (qty <= 0)
            throw new IllegalArgumentException(
                "Quantity must be positive"
            );

        items.put(p, items.getOrDefault(p, 0) + qty);
    }

    double total() {
        double total = 0;

        for (Map.Entry<Product, Integer> e : items.entrySet()) {
            total += e.getKey().price * e.getValue();
        }

        return total;
    }

    void pay(PaymentMethod method) {
        if (method == null)
            throw new IllegalArgumentException(
                "Payment method cannot be null"
            );

        if (status == Status.PAID) {
            System.out.println("Order " + orderId
                    + " has already been paid.");
            return;
        }

        if (items.isEmpty()) {
            System.out.println(
                "Cannot process payment for an empty order."
            );
            return;
        }

        System.out.println("Payment initiated via " + method.name()
                + " for Order " + orderId + ".");

        if (method.processPayment(total())) {
            status = Status.PAID;
            System.out.println("Payment for Order "
                    + orderId + " successful.");
        } else {
            System.out.println("Payment for Order "
                    + orderId + " failed.");
        }

        System.out.println("Order status: "
                + (status == Status.PAID ? "Paid" : "Pending"));
    }
}

public class week8p5 {
    public static void main(String[] args) {
        Order x = new Order("Customer X", "X");
        x.addProduct(new Product("Product A", 20), 2);
        x.addProduct(new Product("Product B", 30), 1);
        x.pay(new CreditCardPayment());

        System.out.println();

        Order y = new Order("Customer Y", "Y");
        y.pay(new CreditCardPayment());

        System.out.println();

        Order z = new Order("Customer Z", "Z");
        z.addProduct(new Product("Product C", 50), 1);
        z.pay(new PayPalPayment());
    }
}
