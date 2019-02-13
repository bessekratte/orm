package pl.nask.agent.component;

public interface ISharedDatabase {

    String DATABASE_URL = "jdbc:sqlite:sqlite.sqlite";
    String DATABASE_USER = "";
    String DATABASE_PASSWORD = "";

    void createTable(Class<?> tClass);
    int insert(Object o);
    boolean update(Object o, int id);
    Object select(Class<?> tClass, int id);
}
