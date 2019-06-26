package еxceptions;

public class InvalidPinCodeException extends RuntimeException {
    public InvalidPinCodeException() {
        super("Invalid pin code!");
    }
}
