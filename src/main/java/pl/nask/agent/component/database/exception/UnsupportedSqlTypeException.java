package pl.nask.agent.component.database.exception;

public class UnsupportedSqlTypeException extends SharedDatabaseException {

    public UnsupportedSqlTypeException(){
        super("a class contains not supported class composition");
    }
}
