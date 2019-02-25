package pl.nask.agent.component.database.sql.creator;

import org.junit.Test;
import pl.nask.agent.component.database.data.entity.MojaKlasaTestowaRozszerzona;

import java.time.LocalDateTime;

public class UpdateStatementTest {

    @Test
    public void getUpdateSQL() {
        MojaKlasaTestowaRozszerzona test =
                new MojaKlasaTestowaRozszerzona("111", "krzysiek", 20, LocalDateTime.now());

        System.out.println(UpdateStatement.getUpdateSQL(test));
    }
}