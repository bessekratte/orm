package pl.nask.agent.component.database.persistent.resultset;

import java.sql.ResultSet;
import java.sql.SQLException;

@Deprecated
public class PathResultSetDispatcher implements ResultSetDispatcher{

    private static final PathResultSetDispatcher instance;

    static {
        instance = new PathResultSetDispatcher();
    }

    public static PathResultSetDispatcher getInstance() {
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

    private PathResultSetDispatcher() {
    }

}
