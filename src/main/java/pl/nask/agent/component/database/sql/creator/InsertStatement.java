package pl.nask.agent.component.database.sql.creator;

import pl.nask.agent.component.database.reflection.ReflectedGetters;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class InsertStatement {

    public static String getInsertSQL(Object object) {
        String tableName = object.getClass().getSimpleName().toLowerCase();
        Map<String, Object> map = ReflectedGetters.doGetters(object);
        return buildInsertStatement(tableName, map);
    }

    private static String buildInsertStatement(String tableName, Map<String, Object> fieldToValueMap) {

        StringBuilder sql = new StringBuilder("INSERT INTO " + tableName + " (");
        Set<String> set = fieldToValueMap.keySet();
        List<String> fields = new ArrayList<>(set);
        List<Object> values = new ArrayList<>();

        // uzupelniam mapowanie field to value
        for (String field : fields) {
            values.add(fieldToValueMap.get(field));
        }

        //Poniższa linia jest zależna od używanej implementacji bazy
        for (String field : fields) {
            sql.append("\"").append(field).append("\"").append(", ");
        }

        sql.delete(sql.length() - 2, sql.length()); // usuniecie ostatniego ", "
        sql.append(") VALUES (");

        // TODO: 20.02.19 tutaj bede musial uzyc swoich mapperow
        //Poniższa linia jest zależna od używanej implementacji bazy
        for (Object value : values) {
            if (value instanceof LocalDateTime) {
                sql.append("\"").append(Timestamp.valueOf((LocalDateTime) value)).append("\"").append(", ");
                continue;
            }
            sql.append("\"").append(value).append("\"").append(", ");
        }

        sql.delete(sql.length() - 2, sql.length()); // usuniecie ostatniego ", "
        sql.append(");");

        return sql.toString();
    }

}