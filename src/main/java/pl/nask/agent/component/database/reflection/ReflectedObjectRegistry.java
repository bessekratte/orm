package pl.nask.agent.component.database.reflection;

import java.util.HashMap;
import java.util.Map;

public class ReflectedObjectRegistry {

    private static ReflectedObjectRegistry instance = new ReflectedObjectRegistry();
    private final Map<Class<?>, ReflectedObject2> registry = new HashMap<>();

    public static synchronized ReflectedObjectRegistry getInstance() {
        if (instance == null) {
            instance = new ReflectedObjectRegistry();
        }
        return instance;
    }

    private ReflectedObjectRegistry() {
    }

    private boolean isRegistered(final Class<?> clazz) {
        return registry.containsKey(clazz);
    }

    public synchronized ReflectedObject2 getReflectedObject(final Class<?> clazz) {
        final ReflectedObject2 object;
        if (isRegistered(clazz)) {
            object = registry.get(clazz);
        } else {
            ReflectedObject2 reflectedObject = new ReflectedObject2(clazz);
            object = registry.put(clazz, reflectedObject);
        }
        return object;
    }

    public synchronized void register(final Class<?> clazz) {
        ReflectedObject2 reflectedObject = registry.get(clazz);
        if (reflectedObject == null) {
            ReflectedObject2 object = new ReflectedObject2(clazz);
            registry.put(clazz, object);
        }
    }

    public synchronized void unregister(final Class<?> clazz) {
        if (isRegistered(clazz)) {
            ReflectedObject2 reflectedObject = registry.get(clazz);
            if (reflectedObject != null) {
                registry.remove(clazz);
            }
        }
    }

}