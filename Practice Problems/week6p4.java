
class LibraryMember {
    protected String memberId;
    protected int borrowLimit;
    protected int booksBorrowed;

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

    public String displayInfo() {
        return "General | Books: " + booksBorrowed;
    }
}

class StudentMember extends LibraryMember {
    private String course;

    public StudentMember(String id, int limit, String course) {
        super(id, limit);
        this.course = course;
    }

    public String getCourse() {
        return course;
    }

    @Override
    public String displayInfo() {
        return "Student | Course: " + course
                + " | Books: " + booksBorrowed;
    }
}

public class week6p4 {
    static String batchPrint(LibraryMember[] members) {
        StringBuilder report = new StringBuilder();

        for (LibraryMember member : members) {
            report.append(member.displayInfo());

            if (member instanceof StudentMember) {
                StudentMember student = (StudentMember) member;
                report.append(" [Course via downcast: ")
                      .append(student.getCourse())
                      .append("]");
            }

            report.append(" | ");
        }

        return report.toString();
    }

    public static void main(String[] args) {
        LibraryMember general = new LibraryMember("LB50", 3);
        StudentMember student = new StudentMember("STU6", 3, "ECE");

        LibraryMember[] members = {general, student};
        System.out.println(batchPrint(members));
    }
}
