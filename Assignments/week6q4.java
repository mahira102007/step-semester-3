
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
    }

    public void attendSession() {
        sessionsAttended++;
    }

    public int getSessionsAttended() {
        return sessionsAttended;
    }

    public String displayInfo() {
        return "Standard | Sessions: " + sessionsAttended;
    }
}

class PremiumMember extends GymMember {
    private String trainerName;

    public PremiumMember(String id, int fee, String trainerName) {
        super(id, fee);
        this.trainerName = trainerName;
    }

    public String getTrainerName() {
        return trainerName;
    }

    @Override
    public String displayInfo() {
        return "Premium | Trainer: " + trainerName
                + " | Sessions: " + sessionsAttended;
    }
}

public class week6q4 {
    static String batchPrint(GymMember[] members) {
        StringBuilder result = new StringBuilder();

        for (GymMember member : members) {
            result.append(member.displayInfo());

            if (member instanceof PremiumMember) {
                PremiumMember premium = (PremiumMember) member;
                result.append(" [Trainer via downcast: ")
                      .append(premium.getTrainerName())
                      .append("]");
            }

            result.append(" | ");
        }

        return result.toString();
    }

    public static void main(String[] args) {
        GymMember standard = new GymMember("MEM6", 1000);
        PremiumMember premium =
                new PremiumMember("MEM7", 2000, "Coach Riya");

        GymMember[] members = {standard, premium};
        System.out.println(batchPrint(members));
    }
}
