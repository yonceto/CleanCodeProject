package atm;


public class AtmImpl implements Atm {
    private BankManagement bankManagementSystem;


    public AtmImpl(BankManagement bankManagementSystem) {
        this.bankManagementSystem = bankManagementSystem;
    }

    //In order to change current Bank management system
    public void setBankManagementSystem(BankManagement bankManagementSystem) {
        this.bankManagementSystem = bankManagementSystem;
    }

    //BasicAccount operations
    public void depositToAccount(String userID, String pinCode, int accountNumber, double amount) {
        RegularUser current = bankManagementSystem.getUser(userID,pinCode);
        if(current != null){
           current.getAccount(accountNumber).deposit(amount);
        }
    }
    public void withdrawFromAccount(String userID, String pinCode, int accountNumber, double amount) {
        RegularUser current = bankManagementSystem.getUser(userID,pinCode);
        if(current != null) {
            current.getAccount(accountNumber).withdraw(amount);
        }
    }
    public double checkBalanceOfAccount(String userID, String pinCode, int accountNumber) {
        double balance = 0;
        RegularUser current = bankManagementSystem.getUser(userID,pinCode);
        if(current != null) {
            balance = current.getAccount(accountNumber).checkBalance();
        }
        return balance;
    }
    //Administrative operations

    public void changePinCode(String userID, String oldPin, String newPin){
        if(bankManagementSystem.getUser(userID).validatePin(oldPin)) {
            bankManagementSystem.getUser(userID).changePin(newPin);
        }
    }
}
