package pl.nask.agent.component.sql.executors;

import java.sql.*;

public class InsertExecutor {

    public static int executeInsert(String url, String sql) {
        try {
            Connection conn = DriverManager.getConnection(url, "", "");
            Statement st = conn.createStatement();
            st.executeUpdate(sql);
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new RuntimeException("Theres problem return last added index");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
