package pl.nask.agent.component.database.sql.creator;

import pl.nask.agent.component.database.persistent.JavaToDatabaseConverter;
import pl.nask.agent.component.database.reflection.ReflectedGetters;
import pl.nask.agent.component.database.reflection.ReflectedAnnotations;

import java.lang.reflect.Field;
import java.util.Map;

public class InsertStatement {

    public static String getInsertSQL(Object object) {

        String tableName = object.getClass().getSimpleName().toLowerCase();
        Map<String, Object> map = ReflectedGetters.doGetters(object);

        /* TODO: 22.02.19 !!!
        ten kawalek musi zostac jakos zmieniony/przeniesiony
        jego celem jest sprawdzenie czy id encji
        jest intem lub integerem (trzeba moze zrobic tez inne typy numeryczne)
        w celu usuniecia go z sql-a insertowego, dzieki czemu
        sqlite sam wygeneruje dla niego wartosc z-auto-inkrementowana
        */

        Field field = ReflectedAnnotations.getFieldBeingId(object.getClass());
        String idField = field.getType().getSimpleName();
        if (idField.equals("Integer") ||
                idField.equals("int")) {
            map.remove(field.getName());
        }

        map = JavaToDatabaseConverter.makeJavaObjectsDatabaseReadable(map);

        return buildInsertStatement(tableName, map);
    }

    private static String buildInsertStatement(String tableName, Map<String, Object> fieldToValueMap) {

        StringBuilder sql = new StringBuilder("INSERT INTO " + tableName + " (");

        fieldToValueMap.forEach((key, value) -> {
            sql.append("\"");
            sql.append(key);
            sql.append("\"");
            sql.append(", ");
        });

        sql.delete(sql.length() - 2, sql.length()); // usuniecie ostatniego ", "
        sql.append(") VALUES (");

        fieldToValueMap.forEach((key, value) -> {
            sql.append("\"");
            sql.append(value);
            sql.append("\"");
            sql.append(", ");
        });

        sql.delete(sql.length() - 2, sql.length()); // usuniecie ostatniego ", "
        sql.append(");");

        return sql.toString();
    }

}