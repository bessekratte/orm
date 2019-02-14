package pl.nask.agent.component.database.exception;

public class NoSuchSetterException extends RuntimeException {

    public NoSuchSetterException() {
        super("used class does not define setter method");
    }
}
