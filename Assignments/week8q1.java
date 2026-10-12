
interface WashType {
    String getName();
    int durationMinutes();
    double charge();
}

class QuickWash implements WashType {
    public String getName() { return "Quick"; }
    public int durationMinutes() { return 30; }
    public double charge() { return 20; }
}

class NormalWash implements WashType {
    public String getName() { return "Normal"; }
    public int durationMinutes() { return 45; }
    public double charge() { return 30; }
}

class HeavyWash implements WashType {
    public String getName() { return "Heavy"; }
    public int durationMinutes() { return 60; }
    public double charge() { return 45; }
}

class WashCycle {
    final String student;
    final WashingMachine machine;
    final WashType type;

    WashCycle(String student, WashingMachine machine, WashType type) {
        this.student = student;
        this.machine = machine;
        this.type = type;
    }
}

class WashingMachine {
    private final String id;
    private WashCycle active;

    WashingMachine(String id) { this.id = id; }

    public String getId() { return id; }
    public boolean isFree() { return active == null; }

    public boolean start(String student, WashType type) {
        if (!isFree()) {
            System.out.println("Machine " + id + " is currently busy.");
            return false;
        }

        active = new WashCycle(student, this, type);

        System.out.printf(
            "%s wash started on %s for %s (%d min). Charge: ₹%.2f.%n",
            type.getName(), id, student,
            type.durationMinutes(), type.charge()
        );
        return true;
    }

    public void complete() {
        if (active == null) {
            System.out.println(id + " has no active cycle.");
            return;
        }

        System.out.println(id + " cycle completed.");
        active = null;
        System.out.println(id + " is now free.");
    }
}

public class week8q1 {
    public static void main(String[] args) {
        WashingMachine m1 = new WashingMachine("M1");
        WashingMachine m2 = new WashingMachine("M2");

        m1.start("Asha", new QuickWash());
        m1.start("Ravi", new HeavyWash());
        m2.start("Ravi", new HeavyWash());

        m1.complete();
        m1.start("Neha", new NormalWash());
    }
}
