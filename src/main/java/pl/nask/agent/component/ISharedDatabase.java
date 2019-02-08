package pl.nask.agent.component;

public interface ISharedDatabase {

    String DATABASE_URL = "jdbc:sqlite:sqlite.sqlite";

    void createTable(Object o);
    int insert(Object o);
    boolean update(int id, Object o);
    Object select(Class clazz, int id);
}
