
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

abstract class Employee {
    private final String name;

    Employee(String name) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Name required");
        this.name = name;
    }

    public String getName() { return name; }
    public abstract boolean isLeaveDurationAllowed(long days);
}

class FullTimeEmployee extends Employee {
    FullTimeEmployee(String name) { super(name); }

    public boolean isLeaveDurationAllowed(long days) {
        return days > 0 && days <= 30;
    }
}

class PartTimeEmployee extends Employee {
    PartTimeEmployee(String name) { super(name); }

    public boolean isLeaveDurationAllowed(long days) {
        return days > 0 && days <= 10;
    }
}

class LeaveRequest {
    enum Status { PENDING, APPROVED, REJECTED }

    private final Employee employee;
    private final LocalDate start;
    private final LocalDate end;
    private Status status = Status.PENDING;

    LeaveRequest(Employee employee, LocalDate start, LocalDate end) {
        if (start == null || end == null || end.isBefore(start))
            throw new IllegalArgumentException("Invalid dates");

        long days = ChronoUnit.DAYS.between(start, end) + 1;

        if (!employee.isLeaveDurationAllowed(days))
            throw new IllegalArgumentException(
                "Leave duration not allowed"
            );

        this.employee = employee;
        this.start = start;
        this.end = end;
    }

    public void review(boolean approve, String reviewer) {
        if (status != Status.PENDING)
            throw new IllegalStateException(
                "Cannot change leave request status from "
                + status + " to another state."
            );

        if (reviewer == null || reviewer.trim().isEmpty())
            throw new IllegalArgumentException("Reviewer required");

        status = approve ? Status.APPROVED : Status.REJECTED;

        System.out.println(employee.getName()
                + "'s leave request (" + start + "-" + end + ") "
                + status.toString().toLowerCase() + ".");
        System.out.println("Status: "
                + status.toString().charAt(0)
                + status.toString().substring(1).toLowerCase() + ".");
    }

    public void trySetPending() {
        if (status != Status.PENDING)
            throw new IllegalStateException(
                "Cannot change leave request status from "
                + status + " to Pending."
            );
    }
}

public class week8p2 {
    public static void main(String[] args) {
        LeaveRequest john = new LeaveRequest(
            new FullTimeEmployee("John"),
            LocalDate.of(2026, 1, 1),
            LocalDate.of(2026, 1, 5)
        );

        System.out.println(
            "Leave request submitted for John (Jan 1-5). Status: Pending."
        );
        john.review(true, "Alice");

        LeaveRequest jane = new LeaveRequest(
            new PartTimeEmployee("Jane"),
            LocalDate.of(2026, 2, 10),
            LocalDate.of(2026, 2, 11)
        );

        System.out.println(
            "Leave request submitted for Jane (Feb 10-11). Status: Pending."
        );
        jane.review(false, "Bob");

        try {
            john.trySetPending();
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }
}
