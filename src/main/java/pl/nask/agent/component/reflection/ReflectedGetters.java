package pl.nask.agent.component.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public class ReflectedGetters {

    public static Map<String, Object> doGetters(Object object) {

        List<Method> methods = ReflectedGetters.getClassGetters(object);
        Map<String, Object> fieldNameToValue = new TreeMap<>();

        for (int i = 0; i < methods.size(); i++) {
            Method method = methods.get(i);
            String field = method.getName().toLowerCase().substring(3);
            try {
                Object returnedValue = method.invoke(object, null);
                fieldNameToValue.put(field, returnedValue);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return fieldNameToValue;
    }

    private static List<Method> getClassGetters(Object o) {
        return Arrays.stream(o.getClass().getDeclaredMethods())
                .filter(ReflectedGetters::isMethodGetter)
                .collect(Collectors.toList());
    }

    private static boolean isMethodGetter(Method method) {
        if (!method.getName().startsWith("get")) return false;
        if (method.getParameterTypes().length != 0) return false;
        if (void.class.equals(method.getReturnType())) return false;
        return true;
    }

}


