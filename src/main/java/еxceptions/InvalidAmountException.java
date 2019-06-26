package еxceptions;

public class InvalidAmountException extends RuntimeException {
    public InvalidAmountException() {
        super("Amount to withdraw is bigger than current balance/overdraft!");
    }
}
