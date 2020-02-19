package orm.persistent.mappers;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class LocalDateTimeMapper implements PersistentMapper<LocalDateTime, Timestamp> {

    private static final PersistentMapper instance;

    static {
        instance = new LocalDateTimeMapper();
    }

    public static PersistentMapper getInstance(){
        return instance;
    }

    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime localDateTime) {
        return Timestamp.valueOf(localDateTime);
    }

    @Override
    public LocalDateTime convertToJavaClass(Timestamp timestamp) {
        return timestamp.toLocalDateTime();
    }

    private LocalDateTimeMapper() {
    }
}
