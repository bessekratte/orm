package pl.nask.agent.component.database.sql.executors;

import pl.nask.agent.component.database.persistent.JavaToDatabaseConverter;
import pl.nask.agent.component.database.reflection.ReflectedSetters;
import pl.nask.agent.component.database.reflection.registry.ReflectedObjectRegistry;

import java.sql.*;
import java.util.Map;

public class SelectExecutor {

    public static <T> T executeSelect(String url, String user, String password, String sql, Class<T> clazz) {

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            rs.next();

            Map<String, Class<?>> fieldNameToFieldType = ReflectedObjectRegistry.getInstance().getReflectedObject(clazz).getMapOfFieldNameToFieldType();

            Map<String, Object> fieldNameToValue =
                    JavaToDatabaseConverter.makeDatabaseObjectAsJavaObjects(fieldNameToFieldType, rs);

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

