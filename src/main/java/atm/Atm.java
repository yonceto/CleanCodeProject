package atm;


public interface Atm {
    void depositToAccount(String userId, String pin, int accountNumber, double amount);
    void withdrawFromAccount(String userId, String pin, int accountNumber, double amount);
    double checkBalanceOfAccount(String userId, String pin, int accountNumber);
    void changePinCode(String userId, String oldPin, String newPin);
}
