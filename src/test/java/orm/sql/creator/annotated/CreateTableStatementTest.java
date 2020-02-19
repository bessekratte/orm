package orm.sql.creator.annotated;

import org.junit.Test;
import orm.reflection.ReflectedAnnotations;
import orm.sql.creator.CreateTableStatement;
import orm.data.entity.MojaKlasaTestowaRozszerzona;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class CreateTableStatementTest {

    @Test
    public void getFieldBeingId() {
        Field fieldBeingId = ReflectedAnnotations.getFieldBeingId(MojaKlasaTestowaRozszerzona.class);
        assertNotNull(fieldBeingId);
        assertEquals("id", fieldBeingId.getName());
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
        String s = CreateTableStatement.buildCreateTableSQL(MojaKlasaTestowaRozszerzona.class);
        assertEquals(
                "CREATE TABLE IF NOT EXISTS mojaklasatestowarozszerzona " +
                        "(id VARCHAR PRIMARY KEY, " +
                        "name VARCHAR, " +
                        "creationTime TIMESTAMP);",
                s);
    }
}