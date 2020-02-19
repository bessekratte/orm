package orm.sql.executors;

import orm.reflection.registry.ReflectedObjectRegistry;
import orm.exception.NoResultsException;
import orm.persistent.JavaToDatabaseConverter;
import orm.reflection.ReflectedSetters;

import java.sql.*;
import java.util.Map;

public class SelectExecutor {

    public static <T> T executeSelect(String url, String user, String password, String sql, Class<T> clazz) {

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            // TODO: 09.04.19 test ponizej nie jest przetestowany integracyjnie !
            if (!rs.next())
                throw new NoResultsException();

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