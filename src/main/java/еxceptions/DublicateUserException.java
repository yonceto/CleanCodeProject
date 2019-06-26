package еxceptions;

public class DublicateUserException extends RuntimeException {
    public DublicateUserException() {
        super("RegularUser with this ID does not exist");

    }
}
