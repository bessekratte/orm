package pl.nask.agent.component.exception;

public class NoSuchSetterException extends RuntimeException {

    public NoSuchSetterException() {
        super("used class does not define setter method");
    }
}
