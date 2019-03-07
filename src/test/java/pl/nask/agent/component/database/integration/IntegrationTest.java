package pl.nask.agent.component.database.integration;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import pl.nask.agent.component.database.data.entity.ExampleEntityIdIsInt;
import pl.nask.agent.component.database.data.entity.ExampleEntityIdIsString;
import pl.nask.agent.component.database.data.entity.MojaKlasaTestowa2;
import pl.nask.agent.component.database.impl.SharedDatabaseImpl;

import java.nio.file.Paths;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class IntegrationTest {

    private static SharedDatabaseImpl db;

    private ExampleEntityIdIsString testingObjectOne;
    private ExampleEntityIdIsInt testingObjectTwo;
    private MojaKlasaTestowa2 testingObjectThree;

    @BeforeClass
    public static void initDatabaseAndTables() {
        db = new SharedDatabaseImpl();
        db.createTable(ExampleEntityIdIsString.class);
        db.createTable(ExampleEntityIdIsInt.class);
        db.createTable(MojaKlasaTestowa2.class);
    }

    //before every test
    @Before
    public void initEnities() {
        testingObjectOne = new ExampleEntityIdIsString("aaa", 10, 20, "sample", LocalDateTime.now(), Paths.get(""));
        testingObjectTwo = new ExampleEntityIdIsInt(10, 20, "sample", LocalDateTime.now(), Paths.get(""));
        testingObjectThree = new MojaKlasaTestowa2("naS", "name", "lastName");
    }

    //after every test
    @After
    public void removeObjectsFromDatabase() {
        db.remove(testingObjectOne);
        db.remove(testingObjectTwo);
        db.remove(testingObjectThree);
    }

    @Test
    public void insertAndSelectSuccessTest() {

        //insert zwraca tylko id, kiedy pole id jest typu numerycznego
        db.insert(testingObjectOne);
        Object idTwo = db.insert(testingObjectTwo);
        Object idThree = db.insert(testingObjectThree);

        //select from db
        testingObjectOne = (ExampleEntityIdIsString) db.select(ExampleEntityIdIsString.class, "aaa");
        testingObjectTwo = (ExampleEntityIdIsInt) db.select(ExampleEntityIdIsInt.class, idTwo);
        testingObjectThree = (MojaKlasaTestowa2) db.select(MojaKlasaTestowa2.class, idThree);

        //assertEqualityFirstObject
        assertEquals(testingObjectOne.getAge(), 10);
        assertEquals(testingObjectOne.getDoubledAge(), Integer.valueOf(20));
        assertEquals(testingObjectOne.getSampleTest(), "sample");
        assertEquals(testingObjectOne.getIdentify(), "aaa");

        //assertEqualitySecondObject
        assertEquals(testingObjectTwo.getAge(), 10);
        assertEquals(testingObjectTwo.getDoubledAge(), Integer.valueOf(20));
        assertEquals(testingObjectTwo.getSampleTest(), "sample");
        assertNull(testingObjectTwo.getPath());

        //assertEqualityThreeObject
        assertEquals(testingObjectThree.getLastName(), "lastName");
        assertEquals(testingObjectThree.getName(), "name");
        assertNull(testingObjectThree.getNaS());
    }

    @Test
    public void insertAndSelectAndUpdateSuccessTest() {

        //insert zwraca tylko id, kiedy pole id jest typu numerycznego
        db.insert(testingObjectOne);
        Object idTwo = db.insert(testingObjectTwo);
        Object idThree = db.insert(testingObjectThree);

        //select from db
        testingObjectOne = (ExampleEntityIdIsString) db.select(ExampleEntityIdIsString.class, "aaa");
        testingObjectTwo = (ExampleEntityIdIsInt) db.select(ExampleEntityIdIsInt.class, idTwo);
        testingObjectThree = (MojaKlasaTestowa2) db.select(MojaKlasaTestowa2.class, idThree);

        //update objects
        testingObjectOne.setAge(100);
        testingObjectOne.setDoubledAge(200);
        testingObjectOne.setSampleTest("testingObjectOne");

        testingObjectTwo.setAge(100);
        testingObjectTwo.setDoubledAge(200);
        testingObjectTwo.setSampleTest("testingObjectTwo");

        testingObjectThree.setLastName("nameLast");
        testingObjectThree.setName("notName");
        testingObjectThree.setNaS("SOS");

        assertTrue(db.update(testingObjectOne));
        assertTrue(db.update(testingObjectTwo));
        assertTrue(db.update(testingObjectThree));

        //once again select from db
        testingObjectOne = (ExampleEntityIdIsString) db.select(ExampleEntityIdIsString.class, "aaa");
        testingObjectTwo = (ExampleEntityIdIsInt) db.select(ExampleEntityIdIsInt.class, idTwo);
        testingObjectThree = (MojaKlasaTestowa2) db.select(MojaKlasaTestowa2.class, idThree);

        //assertEqualityFirstObject
        assertEquals(testingObjectOne.getAge(), 100);
        assertEquals(testingObjectOne.getDoubledAge(), Integer.valueOf(200));
        assertEquals(testingObjectOne.getSampleTest(), "testingObjectOne");
        assertEquals(testingObjectOne.getIdentify(), "aaa");

        //assertEqualitySecondObject
        assertEquals(testingObjectTwo.getAge(), 100);
        assertEquals(testingObjectTwo.getDoubledAge(), Integer.valueOf(200));
        assertEquals(testingObjectTwo.getSampleTest(), "testingObjectTwo");
        assertNull(testingObjectTwo.getPath());

//        assertEqualityThreeObject
        assertEquals(testingObjectThree.getLastName(), "nameLast");
        assertEquals(testingObjectThree.getName(), "notName");
        assertNull(testingObjectThree.getNaS());
    }
}
