package pl.nask.agent.component.database.persistent.resultset;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LocalDateTimeResultSetDispatcher implements ResultSetDispatcher {

    private static final LocalDateTimeResultSetDispatcher instance;

    static {
        instance = new LocalDateTimeResultSetDispatcher();
    }

    public static LocalDateTimeResultSetDispatcher getInstance() {
        return instance;
    }

    @Override
    public Object invokeResultSetGetter(ResultSet rs, String columnName) {
        try {
            return rs.getTimestamp(columnName);
        } catch (SQLException e) {
            throw new RuntimeException(e + "couldn't retrieve " + columnName + " from database");
        }

    }

    private LocalDateTimeResultSetDispatcher() {

    }
}

