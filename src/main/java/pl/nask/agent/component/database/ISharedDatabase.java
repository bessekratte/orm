package pl.nask.agent.component.database;

public interface ISharedDatabase {

    void createTable(Class<?> tClass);
    Object insert(Object o);
    boolean update(Object o);
    Object select(Class<?> tClass, Object id);

/*
// TODO: 19.02.19 docelowa implementacja
import pl.nask.agent.common.core.domain.IEntity;
    void createTable(Class<? extends IEntity> tClass);
    int insert(IEntity o);
    boolean update(IEntity o, int id);
    IEntity select(Class<? extends IEntity> tClass, int id);

*/
}
