
class LibraryMember {
    private static int counter = 0;

    public final int memberNumber;
    protected int borrowLimit;
    protected int booksBorrowed;
    protected String lastGenre;

    public LibraryMember(int borrowLimit) {
        if (borrowLimit <= 0)
            throw new IllegalArgumentException("Invalid borrow limit");

        counter++;
        memberNumber = counter;
        this.borrowLimit = borrowLimit;
    }

    public void borrowBook() {
        if (booksBorrowed < borrowLimit) booksBorrowed++;
    }

    public void borrowBook(String genre) {
        lastGenre = genre;
        borrowBook();
    }

    public int getBooksBorrowed() {
        return booksBorrowed;
    }

    public static int getMembersEnrolled() {
        return counter;
    }

    public String getMembershipCode() {
        return "LIB-" + (100 + memberNumber);
    }

    public static boolean isValidRenewalCode(String code) {
        if (code == null || code.length() != 4) return false;

        return code.charAt(0) == 'R'
                && Character.isDigit(code.charAt(1))
                && Character.isDigit(code.charAt(2))
                && Character.isUpperCase(code.charAt(3));
    }

    public static String processNightlyAudit(LibraryMember[] members) {
        int processed = 0;
        int nullSkipped = 0;
        int facultyCount = 0;
        int regularCount = 0;

        for (LibraryMember member : members) {
            if (member == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (member instanceof FacultyMember)
                facultyCount++;
            else
                regularCount++;
        }

        return processed + " processed | " + nullSkipped
                + " null skipped | " + facultyCount
                + " faculty | " + regularCount + " regular";
    }
}

class FacultyMember extends LibraryMember {
    private String department;

    public FacultyMember(int borrowLimit, String department) {
        super(borrowLimit);
        this.department = department;
    }
}

public class week6p5 {
    public static void main(String[] args) {
        LibraryMember m1 = new LibraryMember(3);

        System.out.println(m1.getMembershipCode());
        System.out.println(m1.memberNumber);
        System.out.println(LibraryMember.getMembersEnrolled());

        System.out.println(LibraryMember.isValidRenewalCode("R12A"));
        System.out.println(LibraryMember.isValidRenewalCode("R1A"));
        System.out.println(LibraryMember.isValidRenewalCode("X12A"));

        m1.borrowBook();
        m1.borrowBook("Fiction");
        System.out.println(m1.getBooksBorrowed());

        LibraryMember[] members = {
            new FacultyMember(5, "Physics"),
            null,
            new LibraryMember(3)
        };

        System.out.println(LibraryMember.processNightlyAudit(members));
    }
}
