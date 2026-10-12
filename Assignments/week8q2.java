
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

abstract class Assignment {
    private final String title;
    private final int maxMarks;
    private final LocalDate dueDate;

    Assignment(String title, int maxMarks, LocalDate dueDate) {
        if (title == null || title.trim().isEmpty()
                || maxMarks <= 0 || dueDate == null)
            throw new IllegalArgumentException("Invalid assignment");

        this.title = title;
        this.maxMarks = maxMarks;
        this.dueDate = dueDate;
    }

    public String getTitle() { return title; }
    public int getMaxMarks() { return maxMarks; }
    public LocalDate getDueDate() { return dueDate; }

    public abstract double applyPenalty(double marks, long lateDays);
}

class CodingAssignment extends Assignment {
    CodingAssignment(String t, int m, LocalDate d) {
        super(t, m, d);
    }

    public double applyPenalty(double marks, long days) {
        return marks * Math.max(0, 1 - 0.10 * days);
    }
}

class WrittenAssignment extends Assignment {
    WrittenAssignment(String t, int m, LocalDate d) {
        super(t, m, d);
    }

    public double applyPenalty(double marks, long days) {
        return marks * Math.max(0, 1 - 0.20 * days);
    }
}

class Submission {
    enum Status { SUBMITTED, GRADED }

    private final String student;
    private final Assignment assignment;
    private final LocalDate date;
    private Status status = Status.SUBMITTED;

    Submission(String student, Assignment assignment, LocalDate date) {
        this.student = student;
        this.assignment = assignment;
        this.date = date;

        long late = Math.max(0,
            ChronoUnit.DAYS.between(assignment.getDueDate(), date));

        System.out.println(student + "'s submission for '"
                + assignment.getTitle() + "' received ("
                + (late == 0 ? "on time" : late + " days late")
                + "). Status: Submitted.");
    }

    public void grade(double awarded) {
        if (status == Status.GRADED)
            throw new IllegalStateException(
                "Cannot resubmit: '" + assignment.getTitle()
                + "' has already been graded."
            );

        if (awarded < 0 || awarded > assignment.getMaxMarks())
            throw new IllegalArgumentException("Marks out of range");

        long late = Math.max(0,
            ChronoUnit.DAYS.between(assignment.getDueDate(), date));

        double finalMarks = assignment.applyPenalty(awarded, late);
        status = Status.GRADED;

        System.out.printf("%s graded: %.0f/%d%n",
            student, finalMarks, assignment.getMaxMarks());

        System.out.println("Status: Graded.");
    }

    public Status getStatus() { return status; }
}

public class week8q2 {
    public static void main(String[] args) {
        Assignment coding = new CodingAssignment(
            "Linked List Lab", 50, LocalDate.of(2026, 3, 10));

        Assignment written = new WrittenAssignment(
            "Design Essay", 50, LocalDate.of(2026, 3, 12));

        Submission a = new Submission(
            "Asha", coding, LocalDate.of(2026, 3, 10));

        Submission r = new Submission(
            "Ravi", written, LocalDate.of(2026, 3, 14));

        a.grade(45);
        r.grade(40);

        try {
            a.grade(45);
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }
}
