package pl.nask.agent.component.database.sql.executors;

import com.fasterxml.jackson.annotation.ObjectIdResolver;
import pl.nask.agent.component.database.exception.UnsupportedSqlTypeException;
import pl.nask.agent.component.database.persistent.DataType;
import pl.nask.agent.component.database.reflection.ReflectedObject;
import pl.nask.agent.component.database.reflection.ReflectedSetters;

import javax.xml.crypto.Data;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SelectExecutor {

    public static Object executeSelect(String url, String user, String password, String sql, Class<?> clazz) {

        // TODO: 18.02.19 trzeba przebudowac ten balagan
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            rs.next();
/**
 * musze zrobic mape
 * nazwa pola - wartosc
 * String lastName = rs.getString("Lname");
 *
 */
            Map<String, Class<?>> fieldNameToFieldType =
                    ReflectedObject.getReflectedObject(clazz).getMapOfFieldNameToFieldType();

            Map<String, Object> fieldNameToValue = fieldNameToFieldType.entrySet().stream()
                    .collect(Collectors.toMap(Map.Entry::getKey,
                            fieldType -> {

                                DataType dataType = DataType.stream()
                                        .filter(data -> data.getClassType().equals(fieldType.getValue()))
                                        .findFirst()
                                        .orElseThrow(RuntimeException::new);

                                Object result = dataType.getResultsetDispatcher()
                                        .invokeResultSetGetter(rs, fieldType.getKey());

                                result = dataType.getMapper()
                                        .convertToJavaClass(result);

                                return result;
                            }));



/*            Map<String, Object> fieldNameToValue = new HashMap<>();

            for (String fieldName : fieldNames) {
                switch (fieldNameToFieldType.get(fieldName)) {
                    case "String": {
                        fieldNameToValue.put(fieldName, rs.getString(fieldName));
                        break;
                    }
                    case "int": {
                        fieldNameToValue.put(fieldName, rs.getInt(fieldName));
                        break;
                    }
                    case "Integer": {
                        fieldNameToValue.put(fieldName, rs.getInt(fieldName));
                        break;
                    }
                    case "Timestamp": {
                        fieldNameToValue.put(fieldName, rs.getTimestamp(fieldName));
                        break;
                    }
                    case "LocalDateTime": {
                        fieldNameToValue.put(fieldName, rs.getTimestamp(fieldName).toLocalDateTime());
                        break;
                    }
                }
            }*/
            conn.close();
            st.close();
            rs.close();
            return ReflectedSetters.doSetters(clazz, fieldNameToValue);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static Object makeObjectDatabaseReadable(Object object) {

        return DataType.stream()
                .filter(dataType -> dataType.getClassType().equals(object.getClass()))
                .findFirst()
                .orElseThrow(UnsupportedSqlTypeException::new)
                .getMapper()
                .convertToJavaClass(object);
    }
}

