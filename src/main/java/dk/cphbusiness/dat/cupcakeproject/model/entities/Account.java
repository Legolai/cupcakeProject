package dk.cphbusiness.dat.cupcakeproject.model.entities;

public class Account
{
    private int balance;

    public Account(int balance)
    {
        this.balance = balance;
    }

    public int deposit(int amount) {
        if(amount > 0){
            balance += amount;
            return balance;
        }
        return balance;
    }

    public int withdraw(int amount) {
        if(amount > 0){
            balance -= amount;
            return balance;
        }
        return balance;
    }

    public int getBalance()
    {
        return balance;
    }
}
