package pl.nask.agent.component.reflection;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ClassReflectionDispatcher {

    public static List<String> getListOfFieldNames(Class clazz) {
        return Arrays.stream(clazz.getDeclaredFields())
                .map(Field::getName)
                .collect(Collectors.toList());
    }

    public static List<String> getListOfClassTypes(Class clazz) {
        return Arrays.stream(clazz.getDeclaredFields())
                .map(field -> field.getType().getSimpleName())
                .collect(Collectors.toList());
    }
}