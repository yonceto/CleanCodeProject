package atm;

import еxceptions.*;

import java.util.HashMap;
import java.util.Map;

public class RegularUser implements User {
    private String userID;
    private String pinCode;
    private Map<Integer, Account> accountNumberToAccount;

    public RegularUser() {
        this.userID = "";
        this.pinCode = "";
        accountNumberToAccount = new HashMap<Integer, Account>();
    }

    public RegularUser(String userID, String pinCode) {
        this.userID = userID;
        this.pinCode = pinCode;
        accountNumberToAccount = new HashMap<Integer, Account>();
    }

    public String getUserID() {
        return this.userID;
    }

    public String getPinCode() {
        return this.pinCode;
    }

    public boolean changePin(String newPin){
        if(newPin.equals(this.pinCode)) {
            throw new InvalidNewPinCodeException();
        }
        this.pinCode = newPin;
        return true;
    }
    public boolean validatePin(String pinCode) {
        if(!this.getPinCode().equals(pinCode)) {
            throw new InvalidPinCodeException();
        }
        return true;
    }

    public void addAccount(Account newAccount) {
        accountNumberToAccount.put(newAccount.getAccountNumber(), newAccount);
    }

    public Account getAccount(int accountNumber) {
        if(accountNumberToAccount.get(accountNumber) == null) {
            throw new NoSuchAccountException();
        }
        return this.accountNumberToAccount.get(accountNumber);
    }
}
