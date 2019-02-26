package pl.nask.agent.component.database.integration;

import org.junit.Test;
import pl.nask.agent.component.database.ISharedDatabase;
import pl.nask.agent.component.database.data.entity.ExampleEntityIdIsInt;
import pl.nask.agent.component.database.data.entity.ExampleEntityIdIsString;
import pl.nask.agent.component.database.data.entity.MojaKlasaPrawdaFalsz;
import pl.nask.agent.component.database.data.entity.MojaKlasaTestowa2;
import pl.nask.agent.component.database.impl.SharedDatabaseImpl;

import java.nio.file.Paths;
import java.time.LocalDateTime;

public class IntegrationTest {

    @Test
    public void test() {
        ISharedDatabase db = new SharedDatabaseImpl();
        db.createTable(ExampleEntityIdIsString.class);
        ExampleEntityIdIsString entity = new ExampleEntityIdIsString("aaa", 10, 20, "sample", LocalDateTime.now(), Paths.get(""));
        db.insert(entity);
        ExampleEntityIdIsString select = (ExampleEntityIdIsString) db.select(ExampleEntityIdIsString.class, "aaa");
        System.out.println(select);
    }

    @Test
    public void test2() {
        ISharedDatabase db = new SharedDatabaseImpl();
        db.createTable(ExampleEntityIdIsInt.class);
        ExampleEntityIdIsInt entity = new ExampleEntityIdIsInt(10, 20, "sample", LocalDateTime.now(), Paths.get(""));
        Object id = db.insert(entity);
        System.out.println(id);
        ExampleEntityIdIsInt select = (ExampleEntityIdIsInt) db.select(ExampleEntityIdIsInt.class, id);
        System.out.println(select);
    }

    @Test
    public void test3() {
        ISharedDatabase db = new SharedDatabaseImpl();
        db.createTable(MojaKlasaTestowa2.class);
        MojaKlasaTestowa2 entity = new MojaKlasaTestowa2("naS", "name", "lastName");
        Object id = db.insert(entity);
        entity = (MojaKlasaTestowa2) db.select(MojaKlasaTestowa2.class, id);
        entity.setLastName("nameLast");
        db.update(entity);
        entity = (MojaKlasaTestowa2) db.select(MojaKlasaTestowa2.class, id);
        System.out.println(entity);
    }

    @Test
    public void test4() {
        ISharedDatabase db = new SharedDatabaseImpl();
        db.createTable(MojaKlasaPrawdaFalsz.class);
        MojaKlasaPrawdaFalsz obj = new MojaKlasaPrawdaFalsz(Boolean.TRUE, true);
        Object id =
                db.insert(obj);
        System.out.println(
                db.select(MojaKlasaPrawdaFalsz.class, id));
    }
}
