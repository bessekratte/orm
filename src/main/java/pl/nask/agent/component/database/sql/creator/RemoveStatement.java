package pl.nask.agent.component.database.sql.creator;

import pl.nask.agent.component.database.reflection.ReflectedAnnotations;
import pl.nask.agent.component.database.reflection.ReflectedGetters;

import java.lang.reflect.Field;
import java.util.Map;

public class RemoveStatement {

    public static String getDeleteSql(Object o) {

        Map<String, Object> map = ReflectedGetters.doGetters(o);

        Field idField = ReflectedAnnotations.getFieldBeingId(o.getClass());

        String tableName = o.getClass().getSimpleName().toLowerCase();
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM")
                .append(" ")
                .append(tableName)
                .append(" ");

        sql.append("WHERE")
                .append(" ")
                .append("\"")
                .append(idField.getName())
                .append("\"")
                .append(" = ")
                .append("\"")
                .append(map.get(idField.getName()))
                .append("\"");

        return sql.toString();
    }

}
