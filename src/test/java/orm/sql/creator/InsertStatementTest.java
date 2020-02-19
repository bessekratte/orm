package orm.sql.creator;

import org.junit.Test;
import orm.data.entity.MojaKlasaTestowa2;

import static org.junit.Assert.*;

public class InsertStatementTest {

    @Test
    public void getInsertObjectSQL() {
        assertEquals("INSERT INTO mojaklasatestowa2 (\"name\", \"lastname\") VALUES (\"name\", \"lastName\");",
                InsertStatement.getInsertSQL(new MojaKlasaTestowa2("naS", "name", "lastName")));
    }
}