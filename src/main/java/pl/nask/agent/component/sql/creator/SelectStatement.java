package pl.nask.agent.component.sql.creator;

import java.util.Map;

public class SelectStatement {

    public static String buildSelectStatement(String tableName, int id) {

        // TODO: 08.02.19 tutaj byc moze beda potrzebne nawiasy na zmienna id w stringbuilderze

        StringBuilder sql = new StringBuilder("SELECT * FROM " + tableName + " ");
        sql.append("WHERE id = " + id);
        return sql.toString();
    }
}
