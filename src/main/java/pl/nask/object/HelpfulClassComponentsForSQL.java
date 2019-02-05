package pl.nask.object;

import pl.nask.object.exception.UnsupportedSqlTypeException;
import pl.nask.sql.map.JavaToSqlConverter;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HelpfulClassComponentsForSQL {


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

    // TODO: 05.02.19 metoda w budowie
    /*public static Map<String, ?> getMapOfRelationFieldToValue(Object object){
        List<String> fieldNames = getListOfFieldNames(object);


    }*/

    public static String getClassName(Object object){
        return object.getClass().getSimpleName();
    }

    /*private static String covertType(Field javaType) {

        if (javaType.getType().toString().equals("class java.lang.String"))
            return "VARCHAR";

        if (javaType.getType().toString().equals("int") ||
                javaType.getType().toString().equals("class java.lang.Integer"))
            return "INTEGER";

        throw new UnsupportedSqlTypeException();
    }*/


}
