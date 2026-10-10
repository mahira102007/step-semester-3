
class LibraryMember {
    protected String memberId;
    protected int borrowLimit;
    protected int booksBorrowed;

    public LibraryMember(String memberId, int borrowLimit) {
        if (memberId == null || memberId.trim().length() < 4)
            throw new IllegalArgumentException("Invalid member ID");
        if (borrowLimit <= 0)
            throw new IllegalArgumentException("Invalid borrow limit");

        this.memberId = memberId;
        this.borrowLimit = borrowLimit;
    }

    public void borrowBook() {
        if (booksBorrowed < borrowLimit) booksBorrowed++;
    }

    public int getBooksBorrowed() {
        return booksBorrowed;
    }

    public String displayInfo() {
        return "General Member | Books Borrowed: " + booksBorrowed;
    }
}

class StudentMember extends LibraryMember {
    protected String course;

    public StudentMember(String id, int limit, String course) {
        super(id, limit);
        this.course = course;
    }

    @Override
    public String displayInfo() {
        return "Student Member | Course: " + course
                + " | Books Borrowed: " + booksBorrowed;
    }
}

class HonorsStudentMember extends StudentMember {
    private int bonusLimit;

    public HonorsStudentMember(String id, int limit,
                               String course, int bonusLimit) {
        super(id, limit, course);
        this.bonusLimit = bonusLimit;
    }

    @Override
    public String displayInfo() {
        return "Honors Student Member | Course: " + course
                + " | Bonus Limit: " + bonusLimit
                + " | Books Borrowed: " + booksBorrowed;
    }
}

class FacultyMember extends LibraryMember {
    private String department;

    public FacultyMember(String id, int limit, String department) {
        super(id, limit);
        this.department = department;
    }

    @Override
    public String displayInfo() {
        return "Faculty Member | Department: " + department
                + " | Books Borrowed: " + booksBorrowed;
    }
}

public class week6p2 {
    static String classifyGeneration(LibraryMember member) {
        if (member instanceof HonorsStudentMember)
            return "Multilevel descendant (3 generations deep)";
        if (member instanceof StudentMember)
            return "Direct student descendant";
        if (member instanceof FacultyMember)
            return "Hierarchical sibling (independent branch)";
        return "Base class member";
    }

    static int getTotalBooksBorrowed(LibraryMember[] members) {
        int total = 0;
        for (LibraryMember member : members) {
            total += member.getBooksBorrowed();
        }
        return total;
    }

    public static void main(String[] args) {
        StudentMember student = new StudentMember("STU2", 3, "CSE");
        HonorsStudentMember honors =
                new HonorsStudentMember("STU3", 3, "ECE", 2);
        FacultyMember faculty =
                new FacultyMember("STU4", 5, "Physics");

        student.borrowBook();
        student.borrowBook();
        honors.borrowBook();
        faculty.borrowBook();
        faculty.borrowBook();
        faculty.borrowBook();

        System.out.println(student.displayInfo());
        System.out.println(honors.displayInfo());
        System.out.println(faculty.displayInfo());

        System.out.println(classifyGeneration(honors));
        System.out.println(classifyGeneration(faculty));

        LibraryMember[] members = {student, honors, faculty};
        System.out.println(getTotalBooksBorrowed(members));
    }
}
