package pl.nask.agent.component.database.reflection;

import pl.nask.agent.component.database.exception.NoSuchSetterException;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public class ReflectedSetters {

    public static Object doSetters(Class<?> clazz, Map<String, Object> fieldNameToValue) {

        try {
            final Object object = clazz.getConstructor().newInstance();
            Map<String, Method> setterMethodsMap = getMapOfFieldNameToSetterMethod(object.getClass());

            fieldNameToValue.keySet().forEach(fieldName -> {
                Method setter = setterMethodsMap.get(fieldName);
                try {
                    setter.invoke(object, fieldNameToValue.get(fieldName));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            return object;
        } catch (Exception e) {
            // TODO: 11.02.19 zrob wlasna klase wyjatku
            throw new RuntimeException(e);
        }
    }

    public static Map<String, Method> getMapOfFieldNameToSetterMethod(Class clazz) {

        List<String> fieldNames = ReflectedObject.getReflectedObject(clazz)
                .getFields()
                .stream()
                .map(Field::getName)
                .collect(Collectors.toList());
        List<Method> methods = getClassSetters(clazz);

        return fieldNames.stream()
                .collect(Collectors.toMap(
                        name -> name,
                        name -> methods.stream().filter(method -> method
                                .getName().toLowerCase()
                                .equals("set".concat(name.toLowerCase())))
                                .findFirst()
                                .orElseThrow(NoSuchSetterException::new)));
    }

    private static List<Method> getClassSetters(Class o) {
        return ReflectedObject.getReflectedObject(o).getMethods()
                .stream().filter(ReflectedSetters::isMethodSetter)
                .collect(Collectors.toList());
    }

    private static boolean isMethodSetter(Method method) {
        if (!method.getName().startsWith("set")) return false;
        if (method.getParameterTypes().length == 0) return false;
        if (!void.class.equals(method.getReturnType())) return false;
        return true;
    }


}



