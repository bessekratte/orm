package pl.nask.agent.component.database.exception;

public class SharedDatabaseException extends RuntimeException {

    public SharedDatabaseException() {
    }

    public SharedDatabaseException(String message) {
        super(message);
    }
}
