
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

abstract class Room {
    private final String number;

    Room(String number) {
        if (number == null || number.trim().isEmpty())
            throw new IllegalArgumentException("Room number required");
        this.number = number;
    }

    public String getNumber() { return number; }
    public abstract double pricePerNight();
}

class StandardRoom extends Room {
    StandardRoom(String n) { super(n); }
    public double pricePerNight() { return 100.0; }
}

class DeluxeRoom extends Room {
    DeluxeRoom(String n) { super(n); }
    public double pricePerNight() { return 160.0; }
}

class Suite extends Room {
    Suite(String n) { super(n); }
    public double pricePerNight() { return 250.0; }
}

class Reservation {
    final String customer;
    final Room room;
    final LocalDate start, end, cancellationDeadline;
    private boolean cancelled;

    Reservation(String customer, Room room, LocalDate start,
                LocalDate end, LocalDate deadline) {
        if (start == null || end == null || !end.isAfter(start))
            throw new IllegalArgumentException("Invalid dates");

        this.customer = customer;
        this.room = room;
        this.start = start;
        this.end = end;
        this.cancellationDeadline = deadline;
    }

    boolean overlaps(LocalDate s, LocalDate e) {
        return !cancelled && s.isBefore(end) && e.isAfter(start);
    }

    double price() {
        return ChronoUnit.DAYS.between(start, end)
                * room.pricePerNight();
    }

    boolean cancel(LocalDate today) {
        if (cancelled || today.isAfter(cancellationDeadline))
            return false;

        cancelled = true;
        return true;
    }
}

public class week8p4 {
    static List<Reservation> reservations = new ArrayList<>();

    static boolean available(Room room, LocalDate start, LocalDate end) {
        for (Reservation r : reservations) {
            if (r.room == room && r.overlaps(start, end))
                return false;
        }
        return true;
    }

    static Reservation reserve(String customer, Room room,
                               LocalDate start, LocalDate end,
                               LocalDate deadline) {
        if (!available(room, start, end)) {
            System.out.println(room.getClass().getSimpleName() + " "
                    + room.getNumber() + " is not available.");
            return null;
        }

        Reservation r =
            new Reservation(customer, room, start, end, deadline);
        reservations.add(r);

        System.out.printf(
            "Reservation confirmed for %s, %s %s (%s-%s). Price: $%.2f%n",
            customer, room.getClass().getSimpleName(),
            room.getNumber(), start, end, r.price()
        );

        return r;
    }

    public static void main(String[] args) {
        Room standard = new StandardRoom("101");

        LocalDate start = LocalDate.of(2026, 1, 1);
        LocalDate end = LocalDate.of(2026, 1, 5);

        System.out.println("Standard Room 101 is "
                + (available(standard, start, end)
                    ? "available" : "not available")
                + " from " + start + " to " + end + ".");

        Reservation a = reserve(
            "Customer A", standard, start, end,
            LocalDate.of(2025, 12, 30)
        );

        reserve("Customer B", standard,
            LocalDate.of(2026, 1, 3),
            LocalDate.of(2026, 1, 7),
            LocalDate.of(2026, 1, 1));

        if (a != null && a.cancel(LocalDate.of(2025, 12, 29))) {
            System.out.println(
                "Reservation for Customer A, Standard Room 101 "
                + "(" + start + "-" + end + ") cancelled successfully."
            );
        }

        reserve("Customer C", new DeluxeRoom("201"),
            LocalDate.of(2026, 2, 10),
            LocalDate.of(2026, 2, 12),
            LocalDate.of(2026, 2, 1));
    }
}
