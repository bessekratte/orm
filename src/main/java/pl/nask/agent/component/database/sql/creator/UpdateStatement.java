package pl.nask.agent.component.database.sql.creator;

import pl.nask.agent.component.database.reflection.ReflectedAnnotations;
import pl.nask.agent.component.database.reflection.ReflectedGetters;

import java.lang.reflect.Field;
import java.util.Map;

public class UpdateStatement {
//    "update employee set name='Michael Sam' where emp_id=1";

//    UPDATE table-name
//    SET column-name = value, column-name = value, ...
//    WHERE condition

    public static String getUpdateSQL(Object o) {

        Map<String, Object> map = ReflectedGetters.doGetters(o);

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
                .forEach(mapper -> {
                    sql.append("\"");
                    sql.append(mapper.getKey());
                    sql.append("\"");
                    sql.append("=");
                    sql.append("\"");
                    sql.append(mapper.getValue());
                    sql.append("\"");
                    sql.append(", ");
                });

        sql.delete(sql.length() - 2, sql.length());
        sql.append(" WHERE").append(" ");
        sql.append("\"");
        sql.append(idField.getName());
        sql.append("\"");
        sql.append(" = ");
        sql.append("\"");
        sql.append(map.get(idField.getName()));
        sql.append("\"");

        return sql.toString();
    }
}
