package pl.nask.agent.component.database.integration;

import org.junit.Test;
import pl.nask.agent.component.database.data.entity.ExampleEntityIdIsInt;
import pl.nask.agent.component.database.ISharedDatabase;
import pl.nask.agent.component.database.data.entity.ExampleEntityIdIsString;
import pl.nask.agent.component.database.data.entity.MojaKlasaTestowa2;
import pl.nask.agent.component.database.impl.SharedDatabaseImpl;

import java.nio.file.Paths;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class IntegrationTest {

    /*
    integrationTest mean testing
    creating -> inserting -> updating -> selecting data
    */

    @Test
    public void integrationTest() {
        ISharedDatabase db = new SharedDatabaseImpl();

        //create table
        db.createTable(ExampleEntityIdIsString.class);

        //create object
        ExampleEntityIdIsString entity = new ExampleEntityIdIsString("aaa", 10, 20, "sample", LocalDateTime.now(), Paths.get(""));

        //insert to db
        db.insert(entity);

        //select from db
        ExampleEntityIdIsString select = (ExampleEntityIdIsString) db.select(ExampleEntityIdIsString.class, "aaa");

        //assertEquality
        assertEquals(select.getAge(), 10);
        assertEquals(select.getDoubledAge(), Integer.valueOf(20));
        assertEquals(select.getSampleTest(), "sample");
        assertEquals(select.getIdentify(), "aaa");
    }

    @Test
    public void integrationTest2() {
        ISharedDatabase db = new SharedDatabaseImpl();

        //create table
        db.createTable(ExampleEntityIdIsInt.class);

        //create object
        ExampleEntityIdIsInt entity = new ExampleEntityIdIsInt(10, 20, "sample", LocalDateTime.now(), Paths.get(""));

        //get id
        Object id = db.insert(entity);

        //select from db
        ExampleEntityIdIsInt select = (ExampleEntityIdIsInt) db.select(ExampleEntityIdIsInt.class, id);

        //assertEquality
        assertEquals(select.getAge(), 10);
        assertEquals(select.getDoubledAge(), Integer.valueOf(20));
        assertEquals(select.getSampleTest(), "sample");
        assertNull(select.getPath());
    }

    @Test
    public void integrationTest3() {
        ISharedDatabase db = new SharedDatabaseImpl();

        //create table
        db.createTable(MojaKlasaTestowa2.class);

        //create object
        MojaKlasaTestowa2 select = new MojaKlasaTestowa2("naS", "name", "lastName");

        //get id
        Object id = db.insert(select);

        //select from db
        select = (MojaKlasaTestowa2) db.select(MojaKlasaTestowa2.class, id);

        //update
        select.setLastName("nameLast");
        select.setName("notName");
        select.setNaS("SOS");
        db.update(select);

        //select again
        select = (MojaKlasaTestowa2) db.select(MojaKlasaTestowa2.class, id);

        //assertEquality
        assertEquals(select.getLastName(), "nameLast");
        assertEquals(select.getName(), "notName");
        assertNull(select.getNaS());
    }
}
