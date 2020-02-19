package orm.persistent.resultset;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IntResultSetDispatcher implements ResultSetDispatcher {

    private static final IntResultSetDispatcher instance;

    static {
        instance = new IntResultSetDispatcher();
    }

    public static IntResultSetDispatcher getInstance() {
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

    private IntResultSetDispatcher() {

    }
}
