package pl.nask.agent.component.database.persistent.mappers;

public class IntMapper implements PersistentMapper<Integer, Integer> {

    private IntMapper(){
    }

    private static final PersistentMapper instance;

    static {
        instance = new IntMapper();
    }

    public static PersistentMapper getInstance(){
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
