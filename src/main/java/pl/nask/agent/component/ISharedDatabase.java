package pl.nask.agent.component;

public interface ISharedDatabase<T> {

    String DATABASE_URL = "jdbc:sqlite:sqlite.sqlite";

    void createTable(Class<T> tClass);
    int insert(T o);
    boolean update(T o, int id);
    T select(Class<T> tClass, int id);
}
