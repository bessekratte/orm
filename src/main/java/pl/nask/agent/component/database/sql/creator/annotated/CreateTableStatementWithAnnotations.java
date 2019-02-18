package pl.nask.agent.component.database.sql.creator.annotated;

import pl.nask.agent.component.database.mapper.TypeConverter;
import pl.nask.agent.component.database.reflection.ReflectedAnnotations;

import java.lang.reflect.Field;
import java.util.List;

public class CreateTableStatementWithAnnotations {

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
        ReflectedAnnotations.getFieldBeingId(tClass).ifPresent(idField -> {
            sql.append(idField.getName());
            sql.append(" ");
            sql.append(TypeConverter.convertToSqlType(idField.getType().getSimpleName()));
            sql.append(" ");
            sql.append("PRIMARY KEY, ");
        });
        return sql.toString();
    }

    public static String buildTableFieldsSQL(Class tClass){
        StringBuilder sql = new StringBuilder();
        List<Field> fields = ReflectedAnnotations.getNotAnnotatedFields(tClass);
        fields.forEach(field -> {
            sql.append(field.getName());
            sql.append(" ");
            sql.append(TypeConverter.convertToSqlType(field.getType().getSimpleName()));
            sql.append(", ");
        });
        sql.delete(sql.length() - 2, sql.length());
        sql.append(");");
        return sql.toString();
    }
}

