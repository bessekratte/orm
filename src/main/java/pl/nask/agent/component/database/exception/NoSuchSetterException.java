package pl.nask.agent.component.database.exception;

public class NoSuchSetterException extends SharedDatabaseException {

    public NoSuchSetterException() {
        super("used class does not define setter method");
    }
}
