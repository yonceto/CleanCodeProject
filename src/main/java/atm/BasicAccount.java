package atm;

import еxceptions.InvalidAmountException;


public  class BasicAccount implements Account {
    protected int accountNumber;
    protected double balance;

    public BasicAccount() {
        this.accountNumber = AccountNumberGenerator.generateAccountNumber();
        this.balance = 0;
    }

    public int getAccountNumber() {
        return this.accountNumber;
    }

    public void withdraw(double amount) {
        validateAmount(amount);
        if(amount > this.balance) {
            throw new InvalidAmountException();
        }
        this.balance -= amount;
    }
    public void deposit(double amount) {
        validateAmount(amount);
        this.balance += amount;
    }

    public double checkBalance() {
        return this.balance;
    }

    void validateAmount(double amount) {
        if(amount < 0) {
            throw new IllegalArgumentException();
        }
    }
}
