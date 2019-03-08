package pl.nask.agent.component.database.sql.creator;

import pl.nask.agent.component.database.persistent.SupportedJavaClass;
import pl.nask.agent.component.database.reflection.ReflectedAnnotations;

import java.lang.reflect.Field;
import java.util.List;

public class CreateTableStatement {

    public static String buildCreateTableSQL(Class tClass){
        StringBuilder sql = new StringBuilder();
        sql.append(buildCreateOptionSQL(tClass));
        sql.append(buildTableIdSQL(tClass));
        sql.append(buildTableFieldsSQL(tClass));
        return sql.toString();
    }

    public static String buildCreateOptionSQL(Class tClass){
        return "CREATE TABLE IF NOT EXISTS " + tClass.getSimpleName().toLowerCase() + " (";
    }

    public static String buildTableIdSQL(Class tClass) {

        StringBuilder sql = new StringBuilder();
        Field idField = ReflectedAnnotations.getFieldBeingId(tClass);
        sql.append(idField.getName());
        sql.append(" ");
        sql.append(makeClassDatabaseWritable(idField));
        sql.append(" ");
        sql.append("PRIMARY KEY, ");
        return sql.toString();
    }

    public static String buildTableFieldsSQL(Class tClass){
        StringBuilder sql = new StringBuilder();
        List<Field> fields = ReflectedAnnotations.getNotAnnotatedFields(tClass);
        fields.forEach(field -> {
            sql.append(field.getName());
            sql.append(" ");
            sql.append(makeClassDatabaseWritable(field));
            sql.append(", ");
        });
        sql.delete(sql.length() - 2, sql.length());
        sql.append(");");
        return sql.toString();
    }

    public static String makeClassDatabaseWritable(Field idField) {
        return SupportedJavaClass.stream().filter(supportedJavaClass -> supportedJavaClass.getClassType().equals(idField.getType()))
                .findFirst()
                .orElseThrow(RuntimeException::new)
                .getSqlType()
                .toString();
    }
}

