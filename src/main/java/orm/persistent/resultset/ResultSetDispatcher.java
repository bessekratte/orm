package orm.persistent.resultset;

import java.sql.ResultSet;

public interface ResultSetDispatcher {

    Object invokeResultSetGetter(ResultSet rs, String fieldName);
}
