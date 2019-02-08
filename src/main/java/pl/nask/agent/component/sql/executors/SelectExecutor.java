package pl.nask.agent.component.sql.executors;

import pl.nask.agent.component.object.ClassAtomizer;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.Map;

public class SelectExecutor {

    public static Object executeSelect(String url, String sql, Class clazz) {

        try {
            Connection conn = DriverManager.getConnection(url, "", "");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            rs.next();
            Map<String, Method> map = ClassAtomizer.getMapOfFieldNameToSetterMethod(TestingClassPerson.class);

            try {
                Constructor s = clazz.getConstructor();
                Object o = s.newInstance();
                map.entrySet().stream().map(set -> {
                    set.getValue().invoke(o, null);
                    Class returnType = set.getValue().getReturnType();
                })


            } catch (Exception e){
                e.printStackTrace();
            }

;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
/*
*          rs = stmt.executeQuery("SELECT Lname FROM Customers WHERE Snum = 2001");
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

class TestingClassPerson {

    private String name;
    private String lastName;
    private int age;

    public TestingClassPerson(String name, String lastName, int age) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}