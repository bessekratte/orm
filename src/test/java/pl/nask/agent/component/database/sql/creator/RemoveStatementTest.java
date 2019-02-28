package pl.nask.agent.component.database.sql.creator;

import org.junit.Test;
import pl.nask.agent.component.database.data.entity.MojaKlasaTestowaGdzieIdToString;

import java.time.LocalDateTime;

public class RemoveStatementTest {

    @Test
    public void getDeleteSql() {
        MojaKlasaTestowaGdzieIdToString obj = new MojaKlasaTestowaGdzieIdToString("id", "name", 100, LocalDateTime.now());
        String sql = RemoveStatement.getDeleteSql(obj);
        System.out.println(sql);
    }
}