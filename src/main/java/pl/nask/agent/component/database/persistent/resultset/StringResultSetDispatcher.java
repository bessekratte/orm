package pl.nask.agent.component.database.persistent.resultset;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StringResultSetDispatcher implements ResultSetDispatcher {

    private static final StringResultSetDispatcher instance;

    static {
        instance = new StringResultSetDispatcher();
    }

    public static StringResultSetDispatcher getInstance() {
        return instance;
    }

    @Override
    public Object invokeResultSetGetter(ResultSet rs, String columnName) {
        try {
            return rs.getString(columnName);
        } catch (SQLException e) {
            throw new RuntimeException(e + "couldn't retrieve " + columnName + " from database");
        }

    }

    private StringResultSetDispatcher() {

    }
}
