package pl.nask.agent.component.database.persistent.resultset;

import java.sql.ResultSet;

public interface ResultSetDispatcher {

    Object invokeResultSetGetter(ResultSet rs, String fieldName);
}
