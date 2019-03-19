package pl.nask.agent.component.database.reflection;

import lombok.Getter;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class ReflectedObject {

    private List<Field> fields;
    private List<Method> methods;
    private static final Map<Class<?>, ReflectedObject> objects;

    static {

        objects = new HashMap<>();
    }

    private ReflectedObject(Class<?> rClass) {

        fields = instateFields(rClass);
        methods = instateMethods(rClass);
        objects.put(rClass, this);
    }

    public static ReflectedObject getReflectedObject(Class<?> clazz) {

        ReflectedObject ref = objects.get(clazz);
        if (ref == null) {
            ref = new ReflectedObject(clazz);
        }
        return ref;
    }

    public Map<String, Class<?>> getMapOfFieldNameToFieldType() {

        return fields.stream().collect(Collectors.toMap(
                Field::getName,
                Field::getType));
    }

    private List<Field> instateFields(Class<?> rClass) {

        return Arrays.stream(rClass.getDeclaredFields())
                .filter(field ->
                        Arrays.stream(field.getDeclaredAnnotations())
                                .noneMatch(annotation -> annotation.toString().equals("@javax.persistence.Transient()")))
                .collect(Collectors.toList());
    }

    private List<Method> instateMethods(Class<?> rClass) {

        return Arrays.stream(rClass.getDeclaredMethods())
                .filter(method ->
                        fields.stream()
                                .anyMatch(field -> method.getName()
                                        .substring(3)
                                        .toLowerCase()
                                        .equals(field.getName().toLowerCase())))
                .collect(Collectors.toList());
    }
}
