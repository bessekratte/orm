package pl.nask.agent.component.database.sql.creator;

import org.junit.Test;
import pl.nask.agent.component.database.data.entity.MojaKlasaTestowa;
import pl.nask.agent.component.database.data.entity.MojaKlasaTestowa2;

import static org.junit.Assert.*;

public class InsertStatementTest {

    @Test
    public void getInsertObjectSQL() {
        assertEquals("INSERT INTO mojaklasatestowa2 (\"lastname\", \"name\") VALUES (\"lastName\", \"name\");",
                InsertStatement.getInsertSQL(new MojaKlasaTestowa2("naS", "name", "lastName")));
    }
}