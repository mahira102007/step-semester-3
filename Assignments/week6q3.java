
import java.util.Arrays;

class GymMember {
    protected String memberId;
    protected int monthlyFee;
    protected int sessionsAttended;

    private int[] lateFeeHistory = new int[10];
    private int feeCount = 0;
    private int totalLateFees = 0;

    public GymMember(String id, int fee) {
        if (id == null || id.trim().length() < 4)
            throw new IllegalArgumentException("Invalid member ID");
        if (fee <= 0)
            throw new IllegalArgumentException("Invalid monthly fee");

        memberId = id;
        monthlyFee = fee;
    }

    public void attendSession() {
        sessionsAttended++;
    }

    public int getSessionsAttended() {
        return sessionsAttended;
    }

    protected void chargeLateFee(int amount) {
        if (amount <= 0)
            throw new IllegalArgumentException("Fee must be positive");
        if (feeCount >= lateFeeHistory.length)
            throw new IllegalStateException("Fee history is full");

        lateFeeHistory[feeCount++] = amount;
        totalLateFees += amount;
    }

    public int[] getLateFeeHistory() {
        return Arrays.copyOf(lateFeeHistory, feeCount);
    }

    public int getTotalLateFees() {
        return totalLateFees;
    }
}

class PremiumMember extends GymMember {
    private String trainerName;

    public PremiumMember(String id, int fee, String trainerName) {
        super(id, fee);
        this.trainerName = trainerName;
    }

    @Override
    protected void chargeLateFee(int amount) {
        super.chargeLateFee(amount / 2);
    }
}

public class week6q3 {
    public static void main(String[] args) {
        PremiumMember p =
                new PremiumMember("MEM5", 2000, "Coach Riya");

        p.chargeLateFee(200);
        System.out.println(p.getTotalLateFees());

        int[] history = p.getLateFeeHistory();
        history[0] = 999;

        System.out.println(Arrays.toString(p.getLateFeeHistory()));
    }
}
