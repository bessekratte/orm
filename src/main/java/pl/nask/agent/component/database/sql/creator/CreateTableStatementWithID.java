package pl.nask.agent.component.database.sql.creator;

import pl.nask.agent.component.database.reflection.ClassReflectionDispatcher;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class CreateTableStatementWithID {

    public static void f(Class tClass) {
        Map<Field, String> fieldToFieldName = ClassReflectionDispatcher.getFieldToFieldName(tClass);
//        Field id = getFieldBeingId(tClass).ifPresent(); // if present stringbuilder.append id TYPE PRIMARY KEY
    }

    public static Optional<Field> getFieldBeingId(Class aClass) {
        return Arrays.stream(aClass.getDeclaredFields())
                .filter(field ->
                        Arrays.stream(field.getDeclaredAnnotations())
                                .anyMatch(annotation -> annotation.toString().equals("@javax.persistence.Id()")))
                .findFirst();
    }

//    @javax.persistence.Transient()

    public static List<Field> getNotAnnotiatedFields(Class tClass) {
        return Arrays.stream(tClass.getDeclaredFields())
                .filter(field ->
                        Arrays.equals(field.getDeclaredAnnotations(), new Annotation[]{}))
                .collect(Collectors.toList());
    }

    public static String buildCreateTableSQL(Class tClass){
        StringBuilder sql = new StringBuilder("CREATE TABLE ");
//      sql.append("IF NOT EXISTS ");     // TODO: 14.02.19 stworz przelacznik ktory bedzie zarzadzal tym fragmentem tj. (create-drop, create, drop) itp.
        sql.append(tClass.getSimpleName()).append(" (");
        getFieldBeingId(tClass).ifPresent(id -> {
            sql.append(id.getName());
            sql.append(" ");
            sql.append(id.getType().getSimpleName()); // TODO: 14.02.19 tutaj jest typ javovy a powinien byc sql-owy

        });
    }


}

