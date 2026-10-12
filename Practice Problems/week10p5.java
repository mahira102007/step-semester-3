
public class week10p5 {
    static abstract class Staff {
        String name;

        Staff(String name) {
            this.name = name;
        }

        abstract String getType();
        abstract int getPay();

        public String toString() {
            return "Payslip[name=" + name
                    + ", type=" + getType()
                    + ", pay=" + getPay() + "]";
        }
    }

    static class FullTime extends Staff {
        int salary;

        FullTime(String name, int salary) {
            super(name);
            this.salary = salary;
        }

        String getType() { return "FullTime"; }
        int getPay() { return salary; }
    }

    static class PartTime extends Staff {
        int hours, rate;

        PartTime(String name, int hours, int rate) {
            super(name);
            this.hours = hours;
            this.rate = rate;
        }

        String getType() { return "PartTime"; }
        int getPay() { return hours * rate; }
    }

    static class Intern extends Staff {
        int stipend;

        Intern(String name, int stipend) {
            super(name);
            this.stipend = stipend;
        }

        String getType() { return "Intern"; }
        int getPay() { return stipend; }
    }

    public static void main(String[] args) {
        Staff[] staff = {
            new FullTime("Asha", 50000),
            new PartTime("Ravi", 80, 300),
            new Intern("Neha", 15000)
        };

        int total = 0;
        Staff topEarner = staff[0];

        for (Staff person : staff) {
            System.out.println(person);
            total += person.getPay();

            if (person.getPay() > topEarner.getPay()) {
                topEarner = person;
            }
        }

        System.out.println("Total " + total);
        System.out.println("top earner " + topEarner.name);
    }
}
