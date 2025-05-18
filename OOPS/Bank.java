class Account {
    int acc_no;
    String name;
    float amount;

    void insert(int a, String n, float amt) {
        acc_no = a;
        name = n;
        amount = amt;
    }

    void deposit(int amt) {
        amount += amt;
        System.out.println(amt + " deposited");
    }

    void withdraw(int amt) {
        if (amt > amount) {
            System.out.println("Insuficient balance");
        } else {
            amount -= amt;
            System.out.println(amt + " withdrawn");
        }
    }

    void checkBalance() {
        System.out.println("Balance is: " + amount);
    }

    void displayRecord() {
        System.out.println(acc_no + " " + name + " " + amount );
    }
}

public class Bank {
    public static void main(String[] args) {
        Account p1 = new Account();
        Account p2 = new Account();

        p1.insert(50010, "Aditya Navgire", 153000);
        p2.insert(50020, "Sonali Patil", 302500);

        p1.displayRecord();
        p2.displayRecord();

        p1.deposit(50);
        p1.checkBalance();
        p2.withdraw(200);
        p2.checkBalance();


    }
}
