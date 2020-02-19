package orm.sql.executors;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTableExecutor {

    public static void executeCreateTable(String url, String user, String password, String sql) {
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement st = conn.createStatement();
            st.executeUpdate(sql);
            conn.close();
            st.close();
        } catch (SQLException e) {
            throw new RuntimeException(e + "problem z tworzeniem tabeli");
        }
    }
}
