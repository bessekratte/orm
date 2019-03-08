package pl.nask.agent.component.database.persistent.mappers;

public class IntegerMapper implements PersistentMapper<Integer, Integer> {

    private IntegerMapper() {
    }

    private static final PersistentMapper instance;

    static {
        instance = new IntegerMapper();
    }

    public static PersistentMapper getInstance() {
        return instance;
    }

    @Override
    public Integer convertToDatabaseColumn(Integer integer) {
        return integer;
    }

    @Override
    public Integer convertToJavaClass(Integer s) {
        return s;
    }
}
