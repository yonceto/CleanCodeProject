package еxceptions;

public class NoSuchAccountException extends RuntimeException {
    public NoSuchAccountException() {
        super("BasicAccount with this number does not exist!");
    }
}
