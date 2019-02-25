package pl.nask.agent.component.database.reflection;

import org.junit.Test;
import pl.nask.agent.component.database.data.entity.MojaKlasaTestowaRozszerzona;

public class ReflectedObjectTest {

    @Test
    public void test() {
        ReflectedObject obj = ReflectedObject.getReflectedObject(MojaKlasaTestowaRozszerzona.class);
        System.out.println(obj);
    }

}