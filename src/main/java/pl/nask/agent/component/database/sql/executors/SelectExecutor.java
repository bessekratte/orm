package pl.nask.agent.component.database.sql.executors;

import pl.nask.agent.component.database.reflection.ClassReflectionDispatcher;
import pl.nask.agent.component.database.reflection.ReflectedObject;
import pl.nask.agent.component.database.reflection.ReflectedSetters;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.HashMap;
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

            List<String> fieldNames = ReflectedObject.getReflectedObject(clazz).getFields().stream().map(Field::getName).collect(Collectors.toList()); // test
            Map<String, String> fieldNameToFieldType = ClassReflectionDispatcher.getFieldNameToFieldType(clazz);
            Map<String, Object> fieldNameToValue = new HashMap<>();

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
            }
            conn.close();
            st.close();
            rs.close();
            return ReflectedSetters.doSetters(clazz, fieldNameToValue);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}

