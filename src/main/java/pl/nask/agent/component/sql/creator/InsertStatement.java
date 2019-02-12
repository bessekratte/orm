package pl.nask.agent.component.sql.creator;

import pl.nask.agent.component.reflection.ReflectedGetters;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class InsertStatement {

    public static String getInsertObjectSQL(Object object) {
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
        for (int i = 0; i < fields.size(); i++) {
            values.add(fieldToValueMap.get(fields.get(i)));
        }

        //Poniższa linia jest zależna od używanej implementacji bazy
        for (int i = 0; i < fields.size(); i++) {
            sql.append("\"" + fields.get(i) + "\"").append(", ");
        }

        sql.delete(sql.length() - 2, sql.length()); // usuniecie ostatniego ", "
        sql.append(") VALUES (");

        //Poniższa linia jest zależna od używanej implementacji bazy
        for (int i = 0; i < values.size(); i++) {
            sql.append("\"" + values.get(i) + "\"").append(", ");
        }
        sql.delete(sql.length() - 2, sql.length()); // usuniecie ostatniego ", "
        sql.append(");");

        return sql.toString();
    }

}