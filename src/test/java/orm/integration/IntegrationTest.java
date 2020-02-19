package orm.integration;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import orm.data.entity.ExampleEntityIdIsInt;
import orm.data.entity.ExampleEntityIdIsString;
import orm.data.entity.MojaKlasaTestowa2;
import orm.data.entity.repository.ExampleEntityIdIsIntRepository;
import orm.data.entity.repository.ExampleEntityIdIsStringRepository;
import orm.data.entity.repository.MojaKlasaTestowa2Repository;
import pl.nask.agent.component.api.database.ISharedDatabase;

import java.nio.file.Paths;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class IntegrationTest {

    //    private static ISharedDatabase dbForStr;
    private static ISharedDatabase<ExampleEntityIdIsString> dbForStr;
    private static ISharedDatabase<ExampleEntityIdIsInt> dbForInt;
    private static ISharedDatabase<MojaKlasaTestowa2> dbForKlasa;

    private ExampleEntityIdIsString testingObjectOne;
    private ExampleEntityIdIsInt testingObjectTwo;
    private MojaKlasaTestowa2 testingObjectThree;

    @BeforeClass
    public static void initDatabaseAndTables() {
        dbForStr = new ExampleEntityIdIsStringRepository();
        dbForInt = new ExampleEntityIdIsIntRepository();
        dbForKlasa = new MojaKlasaTestowa2Repository();
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
        dbForStr.remove(testingObjectOne);
        dbForInt.remove(testingObjectTwo);
        dbForKlasa.remove(testingObjectThree);
    }

    @Test
    public void insertAndSelectSuccessTest() {

        //insert zwraca tylko id, kiedy pole id jest typu numerycznego
        dbForStr.insert(testingObjectOne);
        Object idTwo = dbForInt.insert(testingObjectTwo);
        Object idThree = dbForKlasa.insert(testingObjectThree);

        //select from dbForStr
        testingObjectOne = dbForStr.select("aaa");
        testingObjectTwo = dbForInt.select(idTwo);
        testingObjectThree = dbForKlasa.select(idThree);

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
        dbForStr.insert(testingObjectOne);
        Object idTwo = dbForInt.insert(testingObjectTwo);
        Object idThree = dbForKlasa.insert(testingObjectThree);

        //select from dbForStr
        testingObjectOne = dbForStr.select("aaa");
        testingObjectTwo = dbForInt.select(idTwo);
        testingObjectThree = dbForKlasa.select(idThree);

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

        assertTrue(dbForStr.update(testingObjectOne));
        assertTrue(dbForInt.update(testingObjectTwo));
        assertTrue(dbForKlasa.update(testingObjectThree));

        // once again select from dbForStr
        testingObjectOne = dbForStr.select("aaa");
        testingObjectTwo = dbForInt.select(idTwo);
        testingObjectThree = dbForKlasa.select(idThree);
        // assertEqualityFirstObjec
        assertEquals(testingObjectOne.getAge(), 100);
        assertEquals(testingObjectOne.getDoubledAge(), Integer.valueOf(200));
        assertEquals(testingObjectOne.getSampleTest(), "testingObjectOne");
        assertEquals(testingObjectOne.getIdentify(), "aaa");

        // assertEqualitySecondObject
        assertEquals(testingObjectTwo.getAge(), 100);
        assertEquals(testingObjectTwo.getDoubledAge(), Integer.valueOf(200));
        assertEquals(testingObjectTwo.getSampleTest(), "testingObjectTwo");
        assertNull(testingObjectTwo.getPath());

        // assertEqualityThreeObject
        assertEquals(testingObjectThree.getLastName(), "nameLast");
        assertEquals(testingObjectThree.getName(), "notName");
        assertNull(testingObjectThree.getNaS());
    }
}
