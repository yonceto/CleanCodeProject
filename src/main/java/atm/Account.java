package atm;

public interface Account {
    void withdraw(double amount);
    void deposit(double amount);
    double checkBalance();
    int getAccountNumber();
}
