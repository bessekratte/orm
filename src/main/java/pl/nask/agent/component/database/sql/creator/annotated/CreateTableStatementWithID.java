package pl.nask.agent.component.database.sql.creator.annotated;

import pl.nask.agent.component.database.mapper.TypeConverter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CreateTableStatementWithID {

    public static Optional<Field> getFieldBeingId(Class aClass) {
        return Arrays.stream(aClass.getDeclaredFields())
                .filter(field ->
                        Arrays.stream(field.getDeclaredAnnotations())
                                .anyMatch(annotation -> annotation.toString().equals("@javax.persistence.Id()")))
                .findFirst();
    }

    /*
    public static boolean isFieldAutoincrement(Field field){
        return Arrays.stream(field.getDeclaredAnnotations())
                .anyMatch(annotation -> annotation.toString()
                        // TODO: 18.02.19 jezeli chcesz uzyc jakiegos generatora musisz rozszerzyc metode
                        .equals("@javax.persistence.GeneratedValue(strategy=AUTO, generator=\"\")"));
    }
    */

    public static List<Field> getNotAnnotatedFields(Class tClass) {
        return Arrays.stream(tClass.getDeclaredFields())
                .filter(field ->
                        Arrays.equals(field.getDeclaredAnnotations(), new Annotation[]{}))
                .collect(Collectors.toList());
    }

    public static List<Field> getTransientFields(Class tClass) {

        return Arrays.stream(tClass.getDeclaredFields())
                .filter(field ->
                        Arrays.stream(field.getDeclaredAnnotations())
                                .anyMatch(annotation -> annotation.toString().equals("@javax.persistence.Transient()")))
                .collect(Collectors.toList());
    }

    public static String buildTableIdSQL(Class tClass) {

        StringBuilder sql = new StringBuilder();
        getFieldBeingId(tClass).ifPresent(idField -> {
            sql.append(idField.getName());
            sql.append(" ");
            sql.append(TypeConverter.convertToSqlType(idField.getType().getSimpleName()));
            sql.append(" ");
            sql.append("PRIMARY KEY");
        });
        return sql.toString();
    }

    public static String buildTableFieldsSQL(Class tClass){
        StringBuilder sql = new StringBuilder();
        List<Field> fields = getNotAnnotatedFields(tClass);
        fields.stream().forEach(field -> {
            sql.append(field.getName());
            sql.append(" ");
            sql.append(TypeConverter.convertToSqlType(field.getType().getSimpleName()));
            sql.append(", ");
        });
        sql.delete(sql.length() - 2, sql.length());
        return sql.toString();
    }

    // TODO: 18.02.19 metoda sluzy do implementacji roznych rozwiazan typu: create-drop itp
    public static String buildCreateOptionSQL(Class tClass){
         return "CREATE TABLE IF NOT EXISTS " + tClass.getSimpleName().toLowerCase();
    }

    public static String buildCreateTableSQL(Class tClass){
        StringBuilder sql = new StringBuilder();
        sql.append(buildCreateOptionSQL(tClass));
        sql.append(" (");
        sql.append(buildTableIdSQL(tClass));
        sql.append(", ");
        sql.append(buildTableFieldsSQL(tClass));
        sql.append(");");
        return sql.toString();
    }

}

