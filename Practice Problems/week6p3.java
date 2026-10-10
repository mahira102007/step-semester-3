
import java.util.Arrays;

class LibraryMember {
    protected String memberId;
    protected int borrowLimit;
    protected int booksBorrowed;

    private int[] fineHistory = new int[10];
    private int fineCount = 0;
    private int totalFine = 0;

    public LibraryMember(String id, int limit) {
        if (id == null || id.trim().length() < 4)
            throw new IllegalArgumentException("Invalid member ID");
        if (limit <= 0)
            throw new IllegalArgumentException("Invalid borrow limit");

        memberId = id;
        borrowLimit = limit;
    }

    public void borrowBook() {
        if (booksBorrowed < borrowLimit) booksBorrowed++;
    }

    public int getBooksBorrowed() {
        return booksBorrowed;
    }

    protected void chargeFine(int amount) {
        if (amount <= 0)
            throw new IllegalArgumentException("Fine must be positive");
        if (fineCount == fineHistory.length)
            throw new IllegalStateException("Fine history is full");

        fineHistory[fineCount++] = amount;
        totalFine += amount;
    }

    public int[] getFineHistory() {
        return Arrays.copyOf(fineHistory, fineCount);
    }

    public int getTotalFine() {
        return totalFine;
    }
}

class StudentMember extends LibraryMember {
    private String course;

    public StudentMember(String id, int limit, String course) {
        super(id, limit);
        this.course = course;
    }

    @Override
    protected void chargeFine(int amount) {
        super.chargeFine(amount / 2);
    }
}

public class week6p3 {
    public static void main(String[] args) {
        StudentMember s = new StudentMember("STU5", 3, "CSE");

        s.chargeFine(100);
        System.out.println(s.getTotalFine());

        int[] history = s.getFineHistory();
        history[0] = 999;

        System.out.println(Arrays.toString(s.getFineHistory()));
    }
}
