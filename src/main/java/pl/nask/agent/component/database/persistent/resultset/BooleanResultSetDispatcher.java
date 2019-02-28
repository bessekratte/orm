package pl.nask.agent.component.database.persistent.resultset;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BooleanResultSetDispatcher implements ResultSetDispatcher {

    private static final BooleanResultSetDispatcher instance;

    static {
        instance = new BooleanResultSetDispatcher();
    }

    public static BooleanResultSetDispatcher getInstance() {
        return instance;
    }

    @Override
    public Object invokeResultSetGetter(ResultSet rs, String columnName) {
        try {
            return rs.getInt(columnName);
        } catch (SQLException e) {
            throw new RuntimeException(e + "couldn't retrieve " + columnName + " from database");
        }
    }

    private BooleanResultSetDispatcher() {
    }
}
