package pl.nask.agent.component.sql.executors;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTableExecutor {

    public static void executeCreateTable(String url, String sql) {

        try {
            Connection conn = DriverManager.getConnection(url, "", "");
            Statement st = conn.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
