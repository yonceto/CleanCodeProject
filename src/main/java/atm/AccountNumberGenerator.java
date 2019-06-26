package atm;

public class AccountNumberGenerator {
    static int acountNumber = 0;

    public static int generateAccountNumber() {
        acountNumber++;
        return acountNumber;
    }
}
