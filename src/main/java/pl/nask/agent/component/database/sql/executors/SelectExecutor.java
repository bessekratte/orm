package pl.nask.agent.component.database.sql.executors;

import pl.nask.agent.component.database.persistent.DataType;
import pl.nask.agent.component.database.reflection.ReflectedObject;
import pl.nask.agent.component.database.reflection.ReflectedSetters;

import java.sql.*;
import java.util.Map;
import java.util.stream.Collectors;

public class SelectExecutor {

    public static Object executeSelect(String url, String user, String password, String sql, Class<?> clazz) {

        // TODO: 25.02.19 metoda zbyt nieczytelna
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            rs.next();

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

