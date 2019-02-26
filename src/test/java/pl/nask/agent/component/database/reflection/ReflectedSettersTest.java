package pl.nask.agent.component.database.reflection;

import org.junit.Test;
import pl.nask.agent.component.database.data.entity.MojaKlasaTestowa;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ReflectedSettersTest {

    @Test
    public void doSetters() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "Jan");
        map.put("lastName", "Kowalski");
        map.put("age", 20);
        Object object = ReflectedSetters.doSetters(MojaKlasaTestowa.class, map);
        assertEquals(new MojaKlasaTestowa("Jan", "Kowalski", 20),
                object);
    }

    @Test
    public void getMapOfFieldNameToSetterMethod() {
        Map<String, Method> map = new HashMap<>();
        try {
            map.put("name", MojaKlasaTestowa.class.getMethod("setName", String.class));
            map.put("lastName", MojaKlasaTestowa.class.getMethod("setLastName", String.class));
            map.put("age", MojaKlasaTestowa.class.getMethod("setAge", int.class));
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e + "add ReflectedSetters to your class in order to use shared-database");
        }
        assertEquals(ReflectedSetters.getMapOfFieldNameToSetterMethod(MojaKlasaTestowa.class),
                map);
    }
}