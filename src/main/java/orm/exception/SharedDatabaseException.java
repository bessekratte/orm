package orm.exception;

public class SharedDatabaseException extends RuntimeException {

    public SharedDatabaseException() {
    }

    public SharedDatabaseException(String message) {
        super(message);
    }
}
