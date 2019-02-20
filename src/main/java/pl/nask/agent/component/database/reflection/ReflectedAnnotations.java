package pl.nask.agent.component.database.reflection;

import pl.nask.agent.component.database.exception.NoIdFieldInEntity;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ReflectedAnnotations {

    public static Field getFieldBeingId(Class aClass) {

        return Arrays.stream(aClass.getDeclaredFields())
                .filter(field ->
                        Arrays.stream(field.getDeclaredAnnotations())
                                .anyMatch(annotation -> annotation.toString().equals("@javax.persistence.Id()")))
                .findFirst().orElseThrow(NoIdFieldInEntity::new);
    }

    public static List<Field> getNotTransientFields(Class tClass) {

        return Arrays.stream(tClass.getDeclaredFields())
                .filter(field ->
                        Arrays.stream(field.getDeclaredAnnotations())
                                .noneMatch(annotation -> annotation.toString().equals("@javax.persistence.Transient()")))
                .collect(Collectors.toList());
    }

    public static List<Field> getNotAnnotatedFields(Class tClass) {

        return Arrays.stream(tClass.getDeclaredFields())
                .filter(field ->
                        Arrays.equals(field.getDeclaredAnnotations(), new Annotation[]{}))
                .collect(Collectors.toList());
    }

    /*
    public static boolean isFieldAutoincrement(Field field){
        return Arrays.stream(field.getDeclaredAnnotations())
                .anyMatch(annotation -> annotation.toString()
                        // TODO: 18.02.19 jezeli chcesz uzyc jakiegos generatora musisz rozszerzyc metode
                        .equals("@javax.persistence.GeneratedValue(strategy=AUTO, generator=\"\")"));
    }
    */
}
