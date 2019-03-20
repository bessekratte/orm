package pl.nask.agent.component.database.reflection.registry;

import lombok.Getter;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class ReflectedObject {

    private final List<Field> fields = new ArrayList<>();
    private final List<Method> methods = new ArrayList<>();

    public ReflectedObject(Class<?> clazz) {
        instateFields(clazz);
        instateMethods(clazz);
    }

    public Map<String, Class<?>> getMapOfFieldNameToFieldType() {

        return fields.stream().collect(Collectors.toMap(
                Field::getName,
                Field::getType));
    }

    private void instateFields(Class<?> rClass) {

        Arrays.stream(rClass.getDeclaredFields())
                .filter(field -> Arrays.stream(field.getDeclaredAnnotations())
                        .noneMatch(annotation -> annotation.toString().equals("@javax.persistence.Transient()")))
                .collect(Collectors.toCollection(() -> fields));
    }

    private void instateMethods(Class<?> rClass) {

        Arrays.stream(rClass.getDeclaredMethods())
                .filter(method -> fields.stream()
                        .anyMatch(field -> method.getName()
                                .substring(3)
                                .toLowerCase()
                                .equals(field.getName().toLowerCase())))
                .collect(Collectors.toCollection(() -> methods));
    }
}