
import java.util.*;

abstract class Seat {
    private final String code;

    Seat(String code) {
        if (code == null || code.trim().isEmpty())
            throw new IllegalArgumentException("Seat code required");
        this.code = code;
    }

    public String getCode() { return code; }
    public abstract double getPrice();
}

class RegularSeat extends Seat {
    RegularSeat(String c) { super(c); }
    public double getPrice() { return 150; }
}

class PremiumSeat extends Seat {
    PremiumSeat(String c) { super(c); }
    public double getPrice() { return 250; }
}

class ReclinerSeat extends Seat {
    ReclinerSeat(String c) { super(c); }
    public double getPrice() { return 400; }
}

class Customer {
    final String name;
    Customer(String n) { name = n; }
}

class Show {
    private final String time;
    private final Set<String> booked = new HashSet<>();

    Show(String time) { this.time = time; }

    public String getTime() { return time; }

    boolean bookSeats(List<Seat> seats) {
        Set<String> requested = new HashSet<>();

        for (Seat s : seats) {
            if (booked.contains(s.getCode())
                    || !requested.add(s.getCode())) {
                System.out.println("Seat " + s.getCode()
                        + " is already booked for this show.");
                return false;
            }
        }

        for (Seat s : seats)
            booked.add(s.getCode());

        return true;
    }

    void release(List<Seat> seats) {
        for (Seat s : seats)
            booked.remove(s.getCode());
    }
}

class Booking {
    private final Customer customer;
    private final Show show;
    private final List<Seat> seats;
    private boolean cancelled;

    Booking(Customer c, Show s, List<Seat> seats) {
        customer = c;
        show = s;
        this.seats = new ArrayList<>(seats);
    }

    double total() {
        double total = 0;
        for (Seat s : seats)
            total += s.getPrice();
        return total;
    }

    String codes() {
        List<String> codes = new ArrayList<>();
        for (Seat s : seats)
            codes.add(s.getCode());
        return String.join(", ", codes);
    }

    void confirm() {
        System.out.printf(
            "Booking confirmed for %s: %s. Total: ₹%.2f.%n",
            customer.name, codes(), total()
        );
    }

    void cancel(boolean beforeShow) {
        if (cancelled) {
            System.out.println("Booking already cancelled.");
            return;
        }

        if (!beforeShow) {
            System.out.println("Cannot cancel after the show starts.");
            return;
        }

        show.release(seats);
        cancelled = true;

        System.out.println(customer.name
                + "'s booking cancelled. Seats "
                + codes() + " released.");
    }
}

public class week8q3 {
    static Booking book(Customer c, Show s, Seat... seats) {
        List<Seat> list = Arrays.asList(seats);

        if (list.isEmpty() || list.size() > 6) {
            System.out.println("Booking must contain 1 to 6 seats.");
            return null;
        }

        if (!s.bookSeats(list))
            return null;

        Booking b = new Booking(c, s, list);
        b.confirm();
        return b;
    }

    public static void main(String[] args) {
        Show show = new Show("7 PM");

        Customer asha = new Customer("Asha");
        Customer ravi = new Customer("Ravi");
        Customer neha = new Customer("Neha");

        Booking a = book(asha, show,
            new RegularSeat("A1"),
            new RegularSeat("A2"),
            new PremiumSeat("F5"));

        book(ravi, show, new RegularSeat("A2"));
        book(ravi, show, new ReclinerSeat("R1"));

        if (a != null)
            a.cancel(true);

        book(neha, show, new RegularSeat("A2"));
    }
}
