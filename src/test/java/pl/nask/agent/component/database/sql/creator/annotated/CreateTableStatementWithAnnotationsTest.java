package pl.nask.agent.component.database.sql.creator.annotated;

import org.junit.Test;
import pl.nask.agent.component.database.data.entity.MojaKlasaTestowaRozszerzona;
import pl.nask.agent.component.database.reflection.ReflectedAnnotations;
import pl.nask.agent.component.database.sql.creator.CreateTableStatementWithAnnotations;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class CreateTableStatementWithAnnotationsTest {

    // TODO: 19.02.19 testy do poprawy
    @Test
    public void getFieldBeingId() {
        Field optional = ReflectedAnnotations.getFieldBeingId(MojaKlasaTestowaRozszerzona.class);
        assertNotNull(optional);
    }

    @Test
    public void getNotAnnotatedFields() {
        List<String> actual = ReflectedAnnotations.getNotAnnotatedFields(MojaKlasaTestowaRozszerzona.class)
                .stream()
                .map(Field::getName)
                .collect(Collectors.toList());
        List<String> expected = new ArrayList<>();
        expected.add("name");
        expected.add("creationTime");
        assertEquals(expected, actual);
    }

    @Test
    public void buildCreateTableSQL() {
        String s = CreateTableStatementWithAnnotations.buildCreateTableSQL(MojaKlasaTestowaRozszerzona.class);
        assertEquals(
                "CREATE TABLE IF NOT EXISTS mojaklasatestowarozszerzona " +
                        "(id VARCHAR PRIMARY KEY, " +
                        "name VARCHAR, " +
                        "creationTime TIMESTAMP);",
                s);
    }
}