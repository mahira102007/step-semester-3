
import java.util.*;

public class week10p3 {
    static class Student {
        String roll;
        String name;

        Student(String roll, String name) {
            this.roll = roll;
            this.name = name;
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Student)) return false;

            Student other = (Student) obj;
            return roll.equals(other.roll);
        }

        public int hashCode() {
            return roll.hashCode();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Set<Student> members = new HashSet<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] p = line.split("\\s+");

            if (p[0].equals("ADD")) {
                Student student = new Student(p[1], p[2]);

                if (members.add(student)) {
                    System.out.println("Added");
                } else {
                    System.out.println("duplicate rejected");
                }
            } else if (p[0].equals("CONTAINS")) {
                Student student = new Student(p[1], p[2]);
                System.out.println("contains: "
                        + members.contains(student));
            }
        }

        System.out.println("member count " + members.size());
        sc.close();
    }
}
