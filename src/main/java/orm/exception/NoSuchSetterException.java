package orm.exception;

public class NoSuchSetterException extends SharedDatabaseException {

    public NoSuchSetterException() {
        super("used class does not define setter method");
    }
}
