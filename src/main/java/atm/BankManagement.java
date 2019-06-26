package atm;

import еxceptions.DublicateUserException;
import еxceptions.InvalidPinCodeException;
import еxceptions.NoSuchUserException;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BankManagement {
    private Map<String, RegularUser> userIdtoUser;

    public BankManagement() {
        userIdtoUser = new HashMap<String, RegularUser>();
    }

    public RegularUser getUser(String userID) {
        if(userIdtoUser.get(userID) == null) {
            throw new NoSuchUserException();
        }
        return userIdtoUser.get(userID);
    }

    public RegularUser getUser(String userID, String pin) {
        RegularUser user = getUser(userID);
        if(!user.validatePin(pin)) {
            throw new InvalidPinCodeException();
        }
        return user;
    }

    public void addUser(String userID, String pin) {
        if(userIdtoUser.get(userID) != null) {
            throw new DublicateUserException();
        }
        userIdtoUser.put(userID, new RegularUser(userID, pin));
    }

    public void addNewAccount(String userId, AccountType type) {
        BasicAccount accountToBeAdded  = null;
        switch(type) {
            case Basic:
                accountToBeAdded = new BasicAccount();
                break;
            case Overdraft:
                System.out.print("Enter overdraft maximum:");
                Scanner sc = new Scanner(System.in);
                double overdraftMaximum = sc.nextDouble();
                accountToBeAdded = new OverdraftAccount(overdraftMaximum);
                break;
            case RestrictedWithdraw:
                System.out.print("Enter withdraw restriction:");
                sc = new Scanner(System.in);
                double withdrawRestriction = sc.nextDouble();
                accountToBeAdded = new RestrictedWithdrawAccount(withdrawRestriction);
                break;
        }
        if(accountToBeAdded != null) {
            getUser(userId).addAccount(accountToBeAdded);
        }
    }

    public void addExistingAccount(String userId, Account account) {
        getUser(userId).addAccount(account);
    }

    public void joinAccounts(String currentUserId, String currentUserPin, int accountNumber, String joinUserId) {
        RegularUser current = getUser(currentUserId, currentUserPin);
        if(current != null) {

            Account accountToAdd = current.getAccount(accountNumber);
            addExistingAccount(joinUserId, accountToAdd);

        }
    }
}
