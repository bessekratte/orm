package pl.nask.agent.component.database.sql.creator;

public class SelectStatement {

    public static String getSelectSQL(String tableName, int id) {

        // TODO: 08.02.19 tutaj byc moze beda potrzebne nawiasy na zmienna id w stringbuilderze

        return "SELECT * FROM " + tableName + " " + "WHERE id = " + id;
    }
}
