package pl.nask.agent.component.database;

import pl.nask.agent.common.core.domain.IEntity;

public interface ISharedDatabase {

    String DATABASE_URL = "jdbc:sqlite:sqlite.sqlite";
    String DATABASE_USER = "";
    String DATABASE_PASSWORD = "";

    void createTable(Class<? extends IEntity> tClass); //TODO: niech klasa przyjmuje obiekty implementujace interfejs IEntity
    int insert(IEntity o); //TODO: zamien Obiect na IEntity
    boolean update(IEntity o, int id); //TODO: brak implementacji
    IEntity select(Class<? extends IEntity> tClass, int id); //TODO: niech klasa przyjmuje obiekty implementujace interfejs IEntity

/*riteria select(Class<?>);

    public class Criteria {

        Criteria and();
        Criteria or();
        Criteria equalTo("name", "John");


        List<Class<?>> findAll(Class<?>);
        Class<?> find(Class<?>);
    }*/
}
