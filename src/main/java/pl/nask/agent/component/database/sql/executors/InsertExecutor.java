package pl.nask.agent.component.database.sql.executors;

import java.sql.*;

public class InsertExecutor {

    public static Object executeInsert(String url, String user, String password, String sql) {
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement st = conn.createStatement()) {
            st.executeUpdate(sql);
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                return rs.getObject(1);
            } else {
                throw new RuntimeException("problem z insertowaniem"); //TODO: w tescie trzeba sprawdzic co sie stanie gdy utworzymy tabele bez id korzystajacego z mechnizmow autoinkrementacji np. tabela: id:String, imie: String, nazwisko: String
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
