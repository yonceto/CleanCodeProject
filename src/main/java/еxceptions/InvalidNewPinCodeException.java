package еxceptions;

public class InvalidNewPinCodeException extends RuntimeException {
    public InvalidNewPinCodeException() {
        super("New pin must be different than old pin");
    }
}
