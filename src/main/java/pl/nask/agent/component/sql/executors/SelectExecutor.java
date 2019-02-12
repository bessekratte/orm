package pl.nask.agent.component.sql.executors;

import pl.nask.agent.component.reflection.ReflectedSetters;
import pl.nask.agent.component.reflection.ClassReflectionDispatcher;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelectExecutor {

    public static Object executeSelect(String url, String sql, Class<?> clazz) {

        try {
            Connection conn = DriverManager.getConnection(url, "", "");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            rs.next();
            List<String> fieldNames = ClassReflectionDispatcher.getListOfFieldNames(clazz);
            Map<String, Object> fieldNameToValue = new HashMap<>();

            for (int i = 0 ; i < fieldNames.size(); i++){
                fieldNameToValue.put(fieldNames.get(i), rs.getObject(fieldNames.get(i)));
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

