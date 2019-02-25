package pl.nask.agent.component.database.reflection;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Deprecated
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

    public static Map<String, String> getFieldNameToFieldType(Class clazz){
        Field[] fields = clazz.getDeclaredFields();
        return Arrays.stream(fields).collect(Collectors.toMap(Field::getName,
                x -> x.getType().getSimpleName()));
    }

    public static Map<Field, String> getFieldToFieldName(Class<?> clazz){
        return Arrays.stream(clazz.getDeclaredFields())
                .collect(Collectors.toMap(field -> field,
                        Field::getName));
    }
}