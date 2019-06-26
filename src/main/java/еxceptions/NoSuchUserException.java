package еxceptions;

public class NoSuchUserException extends RuntimeException {
    public NoSuchUserException() {
        super("User with this ID does not exist!");
    }
}
