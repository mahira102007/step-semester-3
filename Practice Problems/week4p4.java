class Student {

    String name;

    static String collegeName;
    static String academicYear;

    static {
        collegeName = "SRM Institute of Science and Technology";
        academicYear = "2026";
        System.out.println("College info loaded");
    }

    Student(String name) {
        this.name = name;
        System.out.println("Student record created: " + this.name);
    }
}

public class week4p4 {
    public static void main(String[] args) {

        String[] names = {
            "Ravi",
            "Meera",
            "Karthik",
            "Divya",
            "Anitha"
        };

        for (String name : names) {
            new Student(name);
        }
    }
}