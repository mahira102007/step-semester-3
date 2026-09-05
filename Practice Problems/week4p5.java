class PaymentAccount {

    void processPayment(double amount) {
        System.out.println("Paid in one go (day-scholar account)");
    }
}

class HostelPaymentAccount extends PaymentAccount {

    @Override
    void processPayment(double amount) {
        System.out.println("Paid in two installments (hostel account)");
    }
}

public class week4p5 {

    static void processPayment(PaymentAccount account, double amount) {
        if (account instanceof HostelPaymentAccount) {
            account.processPayment(amount);
        } else {
            account.processPayment(amount);
        }
    }

    public static void main(String[] args) {

        PaymentAccount[] accounts = {
            new HostelPaymentAccount(),
            new HostelPaymentAccount(),
            new PaymentAccount(),
            new PaymentAccount()
        };

        double amount = 60000;

        int hostelCount = 0;
        int dayScholarCount = 0;

        for (PaymentAccount account : accounts) {

            if (account instanceof HostelPaymentAccount) {
                hostelCount++;
            } else {
                dayScholarCount++;
            }

            processPayment(account, amount);
        }

        System.out.println("Hostel accounts processed: " + hostelCount);
        System.out.println("Day-scholar accounts processed: " + dayScholarCount);
    }
}