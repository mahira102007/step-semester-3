import java.util.Scanner;
class BankAccount{
    int balance;
    BankAccount( int k) {
        balance=k;
    }
        void deposit( int amount){
            if(amount<0){
            System.out.println("inavlid  deposit amount");
        }
        System.out.println("the amount deposited " +  amount);
        System.out.println("the total bank balance is " +(balance+amount));
    
}

void withdraw( int amount){
    if (amount>balance){
        System.out.println("insufficent funds");
    }
    System.out.println("The amount withdrawn is "+ amount);

}
void checkbalance( int ebalance){
System.out.println("the total balnce is " +ebalance);
}




    }





public class ans {
    public static void main(String [] args){
        Scanner sc= new Scanner (System .in);
        int balancegot=sc.nextInt();
       BankAccount b=new BankAccount(balancegot);
       System.out.println("enter trh ammount to be withdrawn");
       int with = sc.nextInt();
       b.withdraw(with);
       System.out.println("enter the amount to be deposited ");
       int depo= sc.nextInt();
       b.deposit(depo);
       int ebalance=balancegot-with+depo;
       b.checkbalance(ebalance);
       sc.close();




    }
}
