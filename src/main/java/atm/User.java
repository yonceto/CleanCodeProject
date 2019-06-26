package atm;

public interface User {
    String getUserID();
    String getPinCode();
    void addAccount(Account account);
    Account getAccount(int accountNumber);
    boolean changePin(String newPin);

}
