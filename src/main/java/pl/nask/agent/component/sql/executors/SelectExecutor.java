package pl.nask.agent.component.sql.executors;

import pl.nask.agent.component.reflection.ReflectedSetters;
import pl.nask.agent.component.reflection.ClassReflectionDispatcher;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelectExecutor {

    public static Object executeSelect(String url, String user, String password, String sql, Class<?> clazz) {

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            rs.next();
            List<String> fieldNames = ClassReflectionDispatcher.getListOfFieldNames(clazz);
            Map<String, Object> fieldNameToValue = new HashMap<>();
            Map<String, String> fieldNameToFieldType = ClassReflectionDispatcher.getFieldNameToFieldType(clazz);

            for (int i = 0; i < fieldNames.size(); i++) {
                switch (fieldNameToFieldType.get(fieldNames.get(i))) {
                    case "String": {
                        fieldNameToValue.put(fieldNames.get(i), rs.getString(fieldNames.get(i)));
                        break;
                    }
                    case "int": {
                        fieldNameToValue.put(fieldNames.get(i), rs.getInt(fieldNames.get(i)));
                        break;
                    }
                    case "Integer": {
                        fieldNameToValue.put(fieldNames.get(i), rs.getInt(fieldNames.get(i)));
                        break;
                    }
                    case "Timestamp": {
                        fieldNameToValue.put(fieldNames.get(i), rs.getTimestamp(fieldNames.get(i)));
                        break;
                    }
                    case "LocalDateTime": {
                        fieldNameToValue.put(fieldNames.get(i), rs.getTimestamp(fieldNames.get(i)).toLocalDateTime());
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

