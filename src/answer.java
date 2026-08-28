import java.util.Scanner;

class BankAccount {
    int balance;

   
    BankAccount(int k) {
        balance = k;
    }

  
    void deposit(int amount) {
        if (amount < 0) {
            System.out.println("Invalid deposit amount");
        } 
        else {
            balance = balance + amount;
            System.out.println("The amount deposited is " + amount);
            System.out.println("The total bank balance is " + balance);
        }
    }

  
    void withdraw(int amount) {
        if (amount < 0) {
            System.out.println("Invalid withdrawal amount");
        } 
        else if (amount > balance) {
            System.out.println("Insufficient funds");
        } 
        else {
            balance = balance - amount;
            System.out.println("The amount withdrawn is " + amount);
            System.out.println("The remaining balance is " + balance);
        }
    }


    void checkbalance() {
        System.out.println("The total balance is " + balance);
    }
}

public class answer{
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the initial balance:");
        int initialBalance = sc.nextInt();

        BankAccount b = new BankAccount(initialBalance);

        System.out.println("Enter the amount to be withdrawn:");
        int withdrawAmount = sc.nextInt();
        b.withdraw(withdrawAmount);

        System.out.println("Enter the amount to be deposited:");
        int depositAmount = sc.nextInt();
        b.deposit(depositAmount);

        b.checkbalance();

        sc.close();
    }
}
