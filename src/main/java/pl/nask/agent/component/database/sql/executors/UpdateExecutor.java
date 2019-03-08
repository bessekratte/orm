package pl.nask.agent.component.database.sql.executors;

import java.sql.*;

public class UpdateExecutor {

    public static boolean executeUpdate(String url, String user, String password, String sql) {

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement st = conn.createStatement()) {
            st.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
