package orm.reflection.registry;

import java.util.HashMap;
import java.util.Map;

public class ReflectedObjectRegistry {

    private static ReflectedObjectRegistry instance = new ReflectedObjectRegistry();
    private final Map<Class<?>, ReflectedObject> registry = new HashMap<>();

    private ReflectedObjectRegistry() {
    }

    public static ReflectedObjectRegistry getInstance() {
        return instance;
    }

    public ReflectedObject getReflectedObject(final Class<?> clazz) {

        if (!isRegistered(clazz))
            register(clazz);
        return registry.get(clazz);
    }

    public void register(final Class<?> clazz) {
        ReflectedObject reflectedObject = registry.get(clazz);
        if (reflectedObject == null) {
            ReflectedObject object = new ReflectedObject(clazz);
            registry.put(clazz, object);
        }
    }

    public void unregister(final Class<?> clazz) {
        if (isRegistered(clazz)) {
            ReflectedObject reflectedObject = registry.get(clazz);
            if (reflectedObject != null) {
                registry.remove(clazz);
            }
        }
    }

    private boolean isRegistered(final Class<?> clazz) {
        return registry.containsKey(clazz);
    }
}