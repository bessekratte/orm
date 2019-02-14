package pl.nask.agent.component.database.mapper;

public interface PersistentConverter<T, U> {

    U convertToDatabaseColumn(T t);
    T convertToEntityAttribute(U u);
}