package orm.exception;

public class NoIdFieldInEntity extends SharedDatabaseException {

    public NoIdFieldInEntity() {
        super("your entity need to mark id field with JPA's @Id ");
    }
}