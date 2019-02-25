package pl.nask.agent.component.database.persistent.mappers;

// TODO: 25.02.19 implementacja prawdopodobnie nadmiarowa na rzecz BooleanMapper

public class BoolMapper implements PersistentMapper<Boolean, Integer> {

    private BoolMapper() {
    }

    private static final PersistentMapper instance;

    static {
        instance = new BoolMapper();
    }

    public static PersistentMapper getInstance() {
        return instance;
    }

    @Override
    public Integer convertToDatabaseColumn(Boolean aBoolean) {
        return aBoolean ? 1 : 0;
    }

    @Override
    public Boolean convertToJavaClass(Integer integer) {
        if (integer == 0)
            return false;
        else if (integer == 1)
            return true;
        else
            throw new IllegalArgumentException("table contain boolean column with wrong value, should be 0 or 1 but is: " + integer);
    }
}
