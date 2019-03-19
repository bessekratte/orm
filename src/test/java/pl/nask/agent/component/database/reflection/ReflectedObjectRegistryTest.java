//package pl.nask.agent.component.database.reflection;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import pl.nask.agent.component.database.data.entity.CzasowaKlasaTestowa;
//import pl.nask.agent.component.database.data.entity.ExampleEntityIdIsString;
//import pl.nask.agent.component.database.data.entity.MojaKlasaTestowa;
//import pl.nask.agent.component.database.reflection.registry.ReflectedObjectRegistry;
//
//import static org.junit.Assert.*;
//
///**
// * Test rejestru klas, które zostały na których została wykonana refleksja
// */
//public class ReflectedObjectRegistryTest {
//
//    private ReflectedObjectRegistry registry;
//
//
//    @Before
//    public void setUp() throws Exception {
//        registry = ReflectedObjectRegistry.getInstance();
//    }
//
//    @After
//    public void tearDown() throws Exception {
//    }
//
//    @Test
//    public void getInstance() {
//        // given
//
//        // when
//
//        // then
//        assertNotNull(registry);
//    }
//
//    @Test
//    public void getReflectedObject() {
//        // given
//        registry.register(CzasowaKlasaTestowa.class);
//        registry.register(ExampleEntityIdIsString.class);
//
//        // when
//
//        // then
//        assertNotNull(registry.getReflectedObject(CzasowaKlasaTestowa.class));
//        assertNotNull(registry.getReflectedObject(ExampleEntityIdIsString.class));
//        assertNull(registry.getReflectedObject(MojaKlasaTestowa.class));
//    }
//
//    @Test
//    public void register() {
//    }
//
//    @Test
//    public void unregister() {
//    }
//}