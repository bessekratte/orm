package pl.nask.object;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

public class ClassAtomization {

    public static Field[] getClassFields(Object object){
        return object.getClass().getDeclaredFields();
    }
/*
    public static Map<String, ?> getClassFieldToValueMap(Object obj){


    }*/



}
