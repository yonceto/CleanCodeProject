package atm;

public class Main {
    public static final String FIRST_USER_ID = "User1";
    public static final String FIRST_USER_PIN = "123";
    public static final String FIRST_USER_NEWPIN = "1234";
    public static final String SECOND_USER_ID = "User2";
    public static final String SECOND_USER_PIN = "456";
    public static final String SECOND_USER_NEWPIN = "4567";
    public static void main(String[] args) {
        BankManagement currentBankManagement = new BankManagement();
        AtmImpl myATMImpl1 = new AtmImpl(currentBankManagement);

        //Add users and account via RegularUser management system
        currentBankManagement.addUser(FIRST_USER_ID, FIRST_USER_PIN);
        currentBankManagement.addUser(SECOND_USER_ID, SECOND_USER_PIN);
        currentBankManagement.addNewAccount(FIRST_USER_ID, AccountType.Basic); //accountNumber = 1
        currentBankManagement.addNewAccount(FIRST_USER_ID, AccountType.Basic); // accountNumber = 2
        currentBankManagement.addNewAccount(SECOND_USER_ID, AccountType.Basic); //accountNumber = 3
        currentBankManagement.addNewAccount(FIRST_USER_ID, AccountType.Overdraft); //accountNumber = 4
        currentBankManagement.addNewAccount(SECOND_USER_ID, AccountType.RestrictedWithdraw); // entered withdraw restriction = 50

        myATMImpl1.withdrawFromAccount(FIRST_USER_ID, FIRST_USER_PIN, 4, 35);
        System.out.println(myATMImpl1.checkBalanceOfAccount(FIRST_USER_ID, FIRST_USER_PIN, 4));

        //Join accounts
        currentBankManagement.joinAccounts(FIRST_USER_ID, FIRST_USER_PIN, 4, SECOND_USER_ID);
        System.out.println(myATMImpl1.checkBalanceOfAccount(SECOND_USER_ID, SECOND_USER_PIN, 4));

        //Add second ATM with same RegularUser management system
        AtmImpl myATMImpl2 = new AtmImpl(currentBankManagement);
        System.out.println(myATMImpl2.checkBalanceOfAccount(SECOND_USER_ID, SECOND_USER_PIN, 4));
        //Money operation via second ATM and check balance via first ATM
        myATMImpl2.depositToAccount(SECOND_USER_ID, SECOND_USER_PIN, 4, 50);
        System.out.println(myATMImpl1.checkBalanceOfAccount(SECOND_USER_ID, SECOND_USER_PIN, 4)); //expected: 15
    }
}
