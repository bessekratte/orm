package pl.nask.agent.component.database.sql.creator;

import pl.nask.agent.component.database.persistent.JavaToDatabaseConverter;
import pl.nask.agent.component.database.reflection.ReflectedAnnotations;
import pl.nask.agent.component.database.reflection.ReflectedGetters;

import java.lang.reflect.Field;
import java.util.Map;

public class UpdateStatement {

    public static String getUpdateSQL(Object o) {

        Map<String, Object> map = ReflectedGetters.doGetters(o);
        map = JavaToDatabaseConverter.makeJavaObjectsDatabaseReadable(map);

        Field idField = ReflectedAnnotations.getFieldBeingId(o.getClass());

        String tableName = o.getClass().getSimpleName().toLowerCase();
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE")
                .append(" ")
                .append(tableName)
                .append(" ")
                .append("SET")
                .append(" ");

        map.entrySet().stream()
                .filter(entry -> !entry.getKey().equals(idField.getName()))
                .forEach(mapper ->
                    sql.append("\"")
                            .append(mapper.getKey())
                            .append("\"")
                            .append("=")
                            .append("\"")
                            .append(mapper.getValue())
                            .append("\"")
                            .append(", ")
                );

        sql.delete(sql.length() - 2, sql.length());
        sql.append(" WHERE")
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
