import java.util.Scanner;

class Patient {
    String patientName;
    double consultationFee;

    Patient(String patientName, double consultationFee) {
        this.patientName = patientName;
        this.consultationFee = consultationFee;
    }

    double calculateDiscount() {
        if (consultationFee >= 2000) {
            return consultationFee * 0.10;
        } else {
            return consultationFee * 0.05;
        }
    }

    double calculateFinalAmount() {
        double discount = calculateDiscount();
        return consultationFee - discount;
    }

    void display() {
        double discount = calculateDiscount();
        double finalAmount = calculateFinalAmount();

        System.out.println("\nPatient Name: " + patientName);
        System.out.println("Original Consultation Fee: ₹" + consultationFee);
        System.out.println("Discount: ₹" + discount);
        System.out.println("Final Amount: ₹" + finalAmount);
    }
}

public class q4
{
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Patient[] patients = new Patient[5];

        for (int i = 0; i < 5; i++) {

            System.out.println("\nEnter details of Patient " + (i + 1));

            System.out.print("Patient Name: ");
            String name = sc.nextLine();

            System.out.print("Consultation Fee: ");
            double fee = sc.nextDouble();

            sc.nextLine();

            patients[i] = new Patient(name, fee);
        }

        System.out.println("HOSPITAL BILL =");

        for (int i = 0; i < 5; i++) {
            patients[i].display();
        }

        sc.close();
    }
}