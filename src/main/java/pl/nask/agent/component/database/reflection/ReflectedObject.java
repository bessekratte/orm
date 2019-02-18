package pl.nask.agent.component.database.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

// TODO: 18.02.19 sens istnienia klasy wciaz sie wazy

public class ReflectedObject {

    private Field[] fields;
    private Method[] methods;

    public ReflectedObject(Class rClass){

        fields = rClass.getDeclaredFields();
        methods = rClass.getDeclaredMethods();
    }
}
