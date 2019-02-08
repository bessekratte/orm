package pl.nask.agent.component.object;

import pl.nask.agent.component.branch.get.Setters;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ClassAtomizer {

    public static List<String> getListOfFieldNames(Object object) {
        return Arrays.stream(object.getClass().getDeclaredFields())
                .map(Field::getName)
                .collect(Collectors.toList());
    }

    public static List<String> getListOfClassTypes(Object object) {
        return Arrays.stream(object.getClass().getDeclaredFields())
                .map(field -> field.getType().getSimpleName())
                .collect(Collectors.toList());
    }

    public static String getClassName(Object object) {
        return object.getClass().getSimpleName();
    }

    public static Map<String, Method> getMapOfFieldNameToSetterMethod(Class obj) {

        List<String> fieldNames = getListOfFieldNames(obj);
        List<Method> methods = getClassSetters(obj);

        Map<String, Method> map = fieldNames.stream()
                .collect(Collectors.toMap(
                        name -> name,
                        method -> methods.stream().filter(x -> x.getName().substring(3).toLowerCase().equals(method.toLowerCase())).findFirst().orElseThrow(RuntimeException::new)
                        ));
        return map;



    }

    private static List<Method> getClassSetters(Object o) {

        return Arrays.stream(o.getClass().getDeclaredMethods())
                .filter(ClassAtomizer::isMethodSetter)
                .collect(Collectors.toList());
    }

    private static boolean isMethodSetter(Method method) {
        if (!method.getName().startsWith("set")) return false;
        if (method.getParameterTypes().length == 0) return false;
        if (!void.class.equals(method.getReturnType())) return false;
        return true;
    }


}
