package pl.nask.agent.component.database.mapper.persistent;

import pl.nask.agent.component.database.mapper.PersistentConverter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class LocalDateTimeConverter implements PersistentConverter<LocalDateTime, Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime localDateTime) {
        return Timestamp.valueOf(localDateTime);
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp timestamp) {
        return timestamp.toLocalDateTime();
    }
}
