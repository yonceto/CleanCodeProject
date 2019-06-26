package atm;

public class Main {
    public static void main(String[] args) {
        BankManagement currentBankManagement = new BankManagement();
        AtmImpl myATMImpl1 = new AtmImpl(currentBankManagement);

        //Add users and account via RegularUser management system
        currentBankManagement.addUser("ABC123", "123");
        currentBankManagement.addUser("DEF345", "345");
        currentBankManagement.addNewAccount("ABC123", AccountType.Basic); //accountNumber = 1
        currentBankManagement.addNewAccount("ABC123", AccountType.Basic); // accountNumber = 2
        currentBankManagement.addNewAccount("DEF345", AccountType.Basic); //accountNumber = 3
        currentBankManagement.addNewAccount("ABC123", AccountType.Overdraft); //accountNumber = 4
        currentBankManagement.addNewAccount("DEF345", AccountType.RestrictedWithdraw); // entered withdraw restriction = 50

        //Money operations via first ATM
        myATMImpl1.depositToAccount("ABC123", "123",1, 50);
        System.out.println(myATMImpl1.checkBalanceOfAccount("ABC123", "123", 1));
        myATMImpl1.withdrawFromAccount("ABC123", "123", 1, 20);
        System.out.println(myATMImpl1.checkBalanceOfAccount("ABC123", "123", 1));
        myATMImpl1.changePinCode("ABC123", "123", "124");
        System.out.println(myATMImpl1.checkBalanceOfAccount("ABC123", "124", 1));
        myATMImpl1.depositToAccount("DEF345", "345", 5, 100);
        myATMImpl1.withdrawFromAccount("DEF345", "345", 5, 30);
        System.out.println(myATMImpl1.checkBalanceOfAccount("DEF345", "345", 5));
       // myATMImpl1.withdraw("DEF345", "345", 5, 60); // throws exception as expected

        myATMImpl1.depositToAccount("ABC123", "124", 2, 25);
        System.out.println(myATMImpl1.checkBalanceOfAccount("ABC123", "124", 2));
        myATMImpl1.withdrawFromAccount("ABC123", "124", 4, 35);
        System.out.println(myATMImpl1.checkBalanceOfAccount("ABC123", "124", 4));

        //Join accounts
        currentBankManagement.joinAccounts("ABC123", "124", 4, "DEF345");
        System.out.println(myATMImpl1.checkBalanceOfAccount("DEF345", "345", 4));

        //Add second ATM with same RegularUser management system
        AtmImpl myATMImpl2 = new AtmImpl(currentBankManagement);
        System.out.println(myATMImpl2.checkBalanceOfAccount("DEF345", "345", 4));
        //Money operation via second ATM and check balance via first ATM
        myATMImpl2.depositToAccount("DEF345", "345", 4, 50);
        System.out.println(myATMImpl1.checkBalanceOfAccount("DEF345", "345", 4)); //expected: 15
    }
}
