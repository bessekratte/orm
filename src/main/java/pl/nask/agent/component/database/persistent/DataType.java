package pl.nask.agent.component.database.persistent;

import lombok.Getter;
import pl.nask.agent.component.database.persistent.mappers.*;
import pl.nask.agent.component.database.persistent.resultset.*;
import pl.nask.agent.component.database.persistent.sql.SqlType;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.stream.Stream;

@Getter
public enum DataType {

    /**
     * All supported types
     */

    INT(
            int.class,
            SqlType.INTEGER,
            IntMapper.getInstance(),
            IntResultSetDispatcher.getInstance()),

    INTEGER(
            Integer.class,
            SqlType.INTEGER,
            IntegerMapper.getInstance(),
            IntResultSetDispatcher.getInstance()),

    STRING(
            String.class,
            SqlType.VARCHAR,
            StringMapper.getInstance(),
            StringResultSetDispatcher.getInstance()),

    TIMESTAMP(
            Timestamp.class,
            SqlType.TIMESTAMP,
            TimestampMapper.getInstance(),
            TimestampResultSetDispatcher.getInstance()),

    LOCAL_DATE_TIME(
            LocalDateTime.class,
            SqlType.TIMESTAMP,
            LocalDateTimeMapper.getInstance(),
            LocalDateTimeResultSetDispatcher.getInstance()),

    BOOLEAN(
            Boolean.class,
            SqlType.INTEGER,
            BooleanMapper.getInstance(),
            BooleanResultSetDispatcher.getInstance()),

    BOOL(
            boolean.class,
            SqlType.INTEGER,
            BooleanMapper.getInstance(),
            BooleanResultSetDispatcher.getInstance());

    private Class<?> classType;
    private SqlType sqlType;
    private PersistentMapper mapper;
    private ResultSetDispatcher resultsetDispatcher;

    DataType(Class<?> classType, SqlType sqlType, PersistentMapper mapper, ResultSetDispatcher resultsetDispatcher) {
        this.classType = classType;
        this.sqlType = sqlType;
        this.mapper = mapper;
        this.resultsetDispatcher = resultsetDispatcher;
    }

    public static Stream<DataType> stream() {
        return Stream.of(DataType.values());
    }
}
