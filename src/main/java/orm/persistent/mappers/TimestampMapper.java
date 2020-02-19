package orm.persistent.mappers;

import java.sql.Timestamp;

public class TimestampMapper implements PersistentMapper<Timestamp, Timestamp> {

    private static final PersistentMapper instance;

    static {
        instance = new TimestampMapper();
    }

    public static PersistentMapper getInstance(){
        return instance;
    }

    @Override
    public Timestamp convertToDatabaseColumn(Timestamp timestamp) {
        return timestamp;
    }

    @Override
    public Timestamp convertToJavaClass(Timestamp timestamp) {
        return timestamp;
    }

    private TimestampMapper() {
    }
}
