package pl.nask.branch;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ClassGetters {

    public List<Method> getClassGetters(Object o) {
        return Arrays.stream(o.getClass().getDeclaredMethods())
                .filter(ClassGetters::isGetter)
                .collect(Collectors.toList());
    }

    private static boolean isGetter(Method method) {
        if (!method.getName().startsWith("get")) return false;
        if (method.getParameterTypes().length != 0) return false;
        if (void.class.equals(method.getReturnType())) return false;
        return true;
    }
}
