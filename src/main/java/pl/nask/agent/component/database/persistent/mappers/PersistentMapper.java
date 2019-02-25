package pl.nask.agent.component.database.persistent.mappers;

/**
 * class take care of types persisting
 * between Java Type Object and Database Type Object
 * @param <T> java object
 * @param <U> database readable object
 */
public interface PersistentMapper<T, U> {
    U convertToDatabaseColumn(T t);
    T convertToJavaClass(U u);
}