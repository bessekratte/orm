package pl.nask.agent.component.sql.creator;

import java.util.Map;

public class SelectStatement {

    /*public static String buildSelectStatement(String tableName, int id) {

        // TODO: 08.02.19 tutaj byc moze beda potrzebne nawiasy na zmienna id w stringbuilderze

        StringBuilder sql = new StringBuilder("SELECT * FROM " + tableName + " ");
        sql.append("WHERE id = " + id);

    }*/

    public void createMapSetterMethodToFieldName(Class clazz){




    }
}

/*

SELECT * FROM table WHERE ID = id
rs.next();
1 - bede tworzyl nowy obiekt
2 - bede wywolywal jego metody settery

refleksja setter method rs.getString
invoke setter

*/

/*

}*/

/*
*     public static void main (String[] args) {
        try {
            String url = "jdbc:msql://200.210.220.1:1114/Demo";
            Connection conn = DriverManager.getConnection(url,"","");
            Statement stmt = conn.createStatement();
            ResultSet rs;

            rs = stmt.executeQuery("SELECT Lname FROM Customers WHERE Snum = 2001");
            while ( rs.next() ) {
                String lastName = rs.getString("Lname");
                System.out.println(lastName);
            }
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }*/