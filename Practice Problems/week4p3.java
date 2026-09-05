class FeeAccount {

    String regNo;
    double totalFee;

    FeeAccount(String regNo, double totalFee) {
        this.regNo = regNo;
        this.totalFee = totalFee;
    }

    final double calculateLateFee(int daysLate) {
        if (daysLate <= 0) {
            return 0;
        }

        return totalFee * daysLate / 10.0;
    }

    final void printSummary(int daysLate) {

        if (daysLate <= 0) {
            System.out.println(regNo + " - On time, no late fee");
        } else {
            double lateFee = calculateLateFee(daysLate);

            System.out.println(
                regNo + " | Total Fee: Rs " + totalFee +
                " | Late Fee: Rs " + lateFee
            );
        }
    }
}

public class week4p3 {
    public static void main(String[] args) {

        String[] regNos = {"RA001", "RA002", "RA003", "RA004"};

        double[] totalFees = {20000, 15000, 18000, 22000};

        int[] daysLate = {10, 0, 5, 2};

        for (int i = 0; i < regNos.length; i++) {
            FeeAccount account =
                new FeeAccount(regNos[i], totalFees[i]);

            account.printSummary(daysLate[i]);
        }
    }
}