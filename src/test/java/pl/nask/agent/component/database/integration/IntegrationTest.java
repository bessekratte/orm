package pl.nask.agent.component.database.integration;

import org.junit.BeforeClass;
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

    private static SharedDatabaseImpl db;
    private ExampleEntityIdIsString objectString;
    private ExampleEntityIdIsInt objectInt;

    //runs once
    @BeforeClass
    public static void init() {
        db = new SharedDatabaseImpl();
    }

    @Test
    public void insertAndSelectAndDeleteEntityWithStringIdSuccessTest() {

        //create table
        db.createTable(ExampleEntityIdIsString.class);

        //create object
        objectString = new ExampleEntityIdIsString("aaa", 10, 20, "sample", LocalDateTime.now(), Paths.get(""));

        //insert to db
        db.insert(objectString);

        //select from db
        objectString = (ExampleEntityIdIsString) db.select(ExampleEntityIdIsString.class, "aaa");

        //assertEquality
        assertEquals(objectString.getAge(), 10);
        assertEquals(objectString.getDoubledAge(), Integer.valueOf(20));
        assertEquals(objectString.getSampleTest(), "sample");
        assertEquals(objectString.getIdentify(), "aaa");

        //delete object
        db.remove(objectString);
    }

    @Test
    public void insertAndSelectAndDeleteEntityWithIntIdSuccessTest() {
        ISharedDatabase db = new SharedDatabaseImpl();

        //create table
        db.createTable(ExampleEntityIdIsInt.class);

        //create object
        objectInt = new ExampleEntityIdIsInt(10, 20, "sample", LocalDateTime.now(), Paths.get(""));

        //get id
        Object id = db.insert(objectInt);

        //select from db
        objectInt = (ExampleEntityIdIsInt) db.select(ExampleEntityIdIsInt.class, id);

        //assertEquality
        assertEquals(objectInt.getAge(), 10);
        assertEquals(objectInt.getDoubledAge(), Integer.valueOf(20));
        assertEquals(objectInt.getSampleTest(), "sample");
        assertNull(objectInt.getPath());

        //delete object
        db.remove(objectInt);
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

        //delete
        db.remove(select);
    }

}
