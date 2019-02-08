package pl.nask.agent.component.branch.get;

import pl.nask.agent.component.object.ClassAtomizer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Setters {


    public static Map<String, Object> doSetters(Object o) {
// TODO: 06.02.19 metoda do przemeblowania

        List<Method> methods = Setters.getClassSetters(o);
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


    private static List<Method> getClassSetters(Object o) {

        return Arrays.stream(o.getClass().getDeclaredMethods())
                .filter(Setters::isMethodSetter)
                .collect(Collectors.toList());
    }

    private static boolean isMethodSetter(Method method) {
        if (!method.getName().startsWith("set")) return false;
        if (method.getParameterTypes().length == 0) return false;
        if (!void.class.equals(method.getReturnType())) return false;
        return true;
    }


}



