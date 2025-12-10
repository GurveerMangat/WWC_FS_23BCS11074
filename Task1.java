import java.util.*;

class InsufficientFundException extends Exception {
    InsufficientFundException(String msg) {
        super(msg);
    }
}

class BankAccount {
    private String accNo;
    private String name;
    protected double balance;

    BankAccount(String accNo, String name, double balance) {
        this.accNo = accNo;
        this.name = name;
        this.balance = balance;
    }

    void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount);
        }
    }

    void withdraw(double amount) throws InsufficientFundException {
        if (amount > balance)
            throw new InsufficientFundException("Insufficient Balance: " + balance);
        balance -= amount;
        System.out.println("Withdrawn: " + amount);
    }

    void printDetails() {
        System.out.println(accNo);
        System.out.println(name);
        System.out.println(balance);
    }
}

class SavingsAccount extends BankAccount {
    private double rate;

    SavingsAccount(String accNo, String name, double balance, double rate) {
        super(accNo, name, balance);
        this.rate = rate;
    }

    void withdraw(double amount) throws InsufficientFundException {
        if (amount > balance)
            throw new InsufficientFundException("Savings Limit Exceeded: " + balance);
        balance -= amount;
        System.out.println("Withdrawn from savings: " + amount);
    }

    void applyInterest() {
        balance += balance * rate / 100;
    }
}

public class Task1 {
    public static void main(String[] args) {

        BankAccount b1 = new BankAccount("101", "Gurveer", 5000);
        b1.deposit(2000);

        try {
            b1.withdraw(9000);
        } catch (InsufficientFundException e) {
            System.out.println(e.getMessage());
        }

        b1.printDetails();

        SavingsAccount s1 = new SavingsAccount("202", "Gurveer", 10000, 5);
        s1.deposit(3000);

        try {
            s1.withdraw(20000);
        } catch (InsufficientFundException e) {
            System.out.println(e.getMessage());
        }

        s1.applyInterest();
        s1.printDetails();
    }
}
