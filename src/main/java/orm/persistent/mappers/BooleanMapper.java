package orm.persistent.mappers;

public class BooleanMapper implements PersistentMapper<Boolean, Integer> {

    private BooleanMapper() {
    }

    private static final PersistentMapper instance;

    static {
        instance = new BooleanMapper();
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
        else throw new IllegalArgumentException("table contain boolean column with wrong value, should be 0 or 1 but is: " + integer);
    }
}
