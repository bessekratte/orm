package pl.nask.agent.component.database.converter;

import pl.nask.agent.component.database.exception.UnsupportedSqlTypeException;

@Deprecated
public class TypeConverter {

    public static String convertToSqlType(String javaType) {

        switch (javaType) {
            case "String":
                return "VARCHAR";
            case "int":
                return "INTEGER";
            case "Integer":
                return "INTEGER";
            case "LocalDateTime":
                return "TIMESTAMP";
            case "Timestamp":
                return "TIMESTAMP";
            default:
                throw new UnsupportedSqlTypeException();
        }
    }

}
