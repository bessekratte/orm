package pl.nask.agent.component.exception;

public class UnsupportedSqlTypeException extends RuntimeException {

    public UnsupportedSqlTypeException(){
        super("a class contains not supported class composition");
    }
}
