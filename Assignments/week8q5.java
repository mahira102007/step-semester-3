
import java.util.*;

interface NotificationChannel {
    void send(String student, String message);
}

class EmailChannel implements NotificationChannel {
    public void send(String student, String message) {
        System.out.println("[Email → " + student + "] " + message);
    }
}

class SmsChannel implements NotificationChannel {
    public void send(String student, String message) {
        System.out.println("[SMS → " + student + "] " + message);
    }
}

class AppChannel implements NotificationChannel {
    public void send(String student, String message) {
        System.out.println("[App → " + student + "] " + message);
    }
}

class Student {
    final String name;
    final String department;
    private final List<NotificationChannel> channels =
        new ArrayList<>();

    Student(String name, String department) {
        this.name = name;
        this.department = department;
    }

    void addChannel(NotificationChannel channel) {
        channels.add(channel);
    }

    List<NotificationChannel> getChannels() {
        return Collections.unmodifiableList(channels);
    }
}

class Notice {
    final String title;
    final Set<String> departments;

    Notice(String title, Set<String> departments) {
        if (title == null || title.trim().isEmpty())
            throw new IllegalArgumentException("Title is required");

        if (departments == null || departments.isEmpty())
            throw new IllegalArgumentException(
                "At least one target department is required"
            );

        this.title = title;
        this.departments = new HashSet<>(departments);
    }
}

class NoticeBoard {
    private final List<Student> students = new ArrayList<>();

    void addStudent(Student student) {
        students.add(student);
    }

    void post(Notice notice) {
        System.out.println("Notice '" + notice.title
                + "' posted to "
                + String.join(", ", new TreeSet<>(notice.departments))
                + ".");

        for (Student student : students) {
            if (notice.departments.contains(student.department)) {
                for (NotificationChannel channel : student.getChannels()) {
                    channel.send(student.name, notice.title);
                }
            }
        }
    }

    void tryPost(String title, Set<String> departments) {
        try {
            post(new Notice(title, departments));
        } catch (IllegalArgumentException e) {
            System.out.println("Cannot post notice: " + e.getMessage());
        }
    }
}

public class week8q5 {
    public static void main(String[] args) {
        NoticeBoard board = new NoticeBoard();

        Student asha = new Student("Asha", "CSE");
        asha.addChannel(new EmailChannel());
        asha.addChannel(new AppChannel());

        Student ravi = new Student("Ravi", "ECE");
        ravi.addChannel(new SmsChannel());

        board.addStudent(asha);
        board.addStudent(ravi);

        board.tryPost(
            "Lab Closed Tomorrow",
            new HashSet<>(Arrays.asList("CSE"))
        );

        board.tryPost(
            "Fee Deadline Extended",
            new HashSet<>(Arrays.asList("CSE", "ECE"))
        );

        board.tryPost("Sports Day", Collections.emptySet());
    }
}
