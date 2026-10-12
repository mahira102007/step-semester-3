
interface MembershipPlan {
    String getName();
    int getMonths();
    double calculateFee();
}

class MonthlyPlan implements MembershipPlan {
    public String getName() { return "Monthly"; }
    public int getMonths() { return 1; }
    public double calculateFee() { return 1000; }
}

class QuarterlyPlan implements MembershipPlan {
    public String getName() { return "Quarterly"; }
    public int getMonths() { return 3; }
    public double calculateFee() { return 1000 * 3 * 0.90; }
}

class AnnualPlan implements MembershipPlan {
    public String getName() { return "Annual"; }
    public int getMonths() { return 12; }
    public double calculateFee() { return 1000 * 12 * 0.75; }
}

class Membership {
    enum Status { ACTIVE, FROZEN, EXPIRED }

    private Status status = Status.ACTIVE;
    private final String member;
    private final MembershipPlan plan;

    Membership(String member, MembershipPlan plan) {
        this.member = member;
        this.plan = plan;

        System.out.printf(
            "%s membership created for %s. Fee: ₹%.2f. Status: Active.%n",
            plan.getName(), member, plan.calculateFee()
        );
    }

    public void checkIn() {
        if (status != Status.ACTIVE) {
            System.out.println("Check-in denied: " + member
                    + "'s membership is " + status + ".");
            return;
        }

        System.out.println(member + " checked in successfully.");
    }

    public void freeze() {
        if (status == Status.EXPIRED) {
            System.out.println("Cannot freeze an Expired membership.");
            return;
        }

        if (status == Status.FROZEN) {
            System.out.println(member
                    + "'s membership is already Frozen.");
            return;
        }

        status = Status.FROZEN;
        System.out.println(member
                + "'s membership frozen. Status: Frozen.");
    }

    public void unfreeze() {
        if (status == Status.EXPIRED) {
            System.out.println("Cannot unfreeze an Expired membership.");
            return;
        }

        if (status == Status.FROZEN) {
            status = Status.ACTIVE;
            System.out.println(member
                    + "'s membership unfrozen. Status: Active.");
        }
    }

    public void expire() {
        status = Status.EXPIRED;
        System.out.println(member
                + "'s membership expired. Status: Expired.");
    }
}

public class week8q4 {
    public static void main(String[] args) {
        Membership asha =
            new Membership("Asha", new QuarterlyPlan());

        Membership ravi =
            new Membership("Ravi", new MonthlyPlan());

        asha.checkIn();
        asha.freeze();
        asha.checkIn();

        ravi.expire();
        ravi.freeze();
    }
}
