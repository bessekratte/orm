package pl.nask.agent.component.database.persistent.resultset;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TimestampResultSetDispatcher implements ResultSetDispatcher {

    private static final TimestampResultSetDispatcher instance;

    static {
        instance = new TimestampResultSetDispatcher();
    }

    public static TimestampResultSetDispatcher getInstance() {
        return instance;
    }

    @Override
    public Object invokeResultSetGetter(ResultSet rs, String columnName) {
        try {
            return rs.getDate(columnName);
        } catch (SQLException e) {
            throw new RuntimeException(e + "couldn't retrieve " + columnName + " from database");
        }

    }

    private TimestampResultSetDispatcher() {

    }

}
