package еxceptions;

public class WithdrawException extends RuntimeException {
    public WithdrawException() {
        super("Withdraw amount is bigger than restriction");
    }
}
