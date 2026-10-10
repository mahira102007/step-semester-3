class GymMember {
    protected String memberId;
    protected int monthlyFee;
    protected int sessionsAttended;

    public GymMember(String memberId, int monthlyFee) {
        if (memberId == null || memberId.trim().length() < 4)
            throw new IllegalArgumentException("Invalid member ID");
        if (monthlyFee <= 0)
            throw new IllegalArgumentException("Invalid monthly fee");

        this.memberId = memberId;
        this.monthlyFee = monthlyFee;
        this.sessionsAttended = 0;
    }

    public void attendSession() {
        sessionsAttended++;
    }

    public int getSessionsAttended() {
        return sessionsAttended;
    }

    public String displayInfo() {
        return "Standard Member | Sessions: " + sessionsAttended;
    }
}

class PremiumMember extends GymMember {
    protected String trainerName;

    public PremiumMember(String id, int fee, String trainerName) {
        super(id, fee);
        this.trainerName = trainerName;
    }

    @Override
    public String displayInfo() {
        return "Premium Member | Trainer: " + trainerName
                + " | Sessions: " + sessionsAttended;
    }
}

class EliteMember extends PremiumMember {
    private String lockerNumber;

    public EliteMember(String id, int fee, String trainerName,
                       String lockerNumber) {
        super(id, fee, trainerName);
        this.lockerNumber = lockerNumber;
    }

    @Override
    public String displayInfo() {
        return "Elite Member | Trainer: " + trainerName
                + " | Locker: " + lockerNumber
                + " | Sessions: " + sessionsAttended;
    }
}

class GroupClassMember extends GymMember {
    private String className;

    public GroupClassMember(String id, int fee, String className) {
        super(id, fee);
        this.className = className;
    }

    @Override
    public String displayInfo() {
        return "Group Class Member | Class: " + className
                + " | Sessions: " + sessionsAttended;
    }
}

public class week6q2 {
    static String classifyGeneration(GymMember member) {
        if (member instanceof EliteMember)
            return "Multilevel descendant (3 generations deep)";
        if (member instanceof PremiumMember)
            return "Direct premium descendant";
        if (member instanceof GroupClassMember)
            return "Hierarchical sibling (independent branch)";
        return "Base class member";
    }

    static int getTotalSessionsAttended(GymMember[] members) {
        int total = 0;
        for (GymMember member : members) {
            total += member.getSessionsAttended();
        }
        return total;
    }

    public static void main(String[] args) {
        GymMember standard = new GymMember("MEM1", 1000);
        PremiumMember premium =
                new PremiumMember("MEM2", 2000, "Coach Riya");
        EliteMember elite =
                new EliteMember("MEM3", 3000, "Coach Arjun", "L12");
        GroupClassMember group =
                new GroupClassMember("MEM4", 1500, "Zumba");

        System.out.println(standard.displayInfo());
        System.out.println(premium.displayInfo());
        System.out.println(elite.displayInfo());
        System.out.println(group.displayInfo());

        System.out.println(classifyGeneration(elite));
        System.out.println(classifyGeneration(group));

        premium.attendSession();
        premium.attendSession();
        premium.attendSession();

        elite.attendSession();
        elite.attendSession();

        group.attendSession();
        group.attendSession();
        group.attendSession();
        group.attendSession();

        GymMember[] members = {premium, elite, group};
        System.out.println(getTotalSessionsAttended(members));
    }
}
