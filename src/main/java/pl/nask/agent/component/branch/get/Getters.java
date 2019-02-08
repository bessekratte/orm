package pl.nask.agent.component.branch.get;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * this class is invoking getters methods and is returning a list with returned values
 * on unknown class object. I need this class for somehow describe an object
 *
 */

public class Getters {

    public static Map<String, Object> doGetters(Object o) {
// TODO: 06.02.19 metoda do przemeblowania

        List<Method> methods = Getters.getClassGetters(o);
        Map<String, Object> nameToValue = new TreeMap<>();

        for (int i = 0; i < methods.size(); i++) {
            Method method = methods.get(i);
            String field = method.getName().toLowerCase().substring(3);
            try {
                Object returnedValue = method.invoke(o, null);
                nameToValue.put(field, returnedValue);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return nameToValue;
    }

    private static List<Method> getClassGetters(Object o) {
        return Arrays.stream(o.getClass().getDeclaredMethods())
                .filter(Getters::isMethodGetter)
                .collect(Collectors.toList());
    }

    private static boolean isMethodGetter(Method method) {
        if (!method.getName().startsWith("get")) return false;
        if (method.getParameterTypes().length != 0) return false;
        if (void.class.equals(method.getReturnType())) return false;
        return true;
    }

}


