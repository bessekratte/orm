package pl.nask.agent.component.database;

/**
 * @author Mateusz Kierznowski
 */
public interface ISharedDatabase {

    /**
     * @param tClass - klasa encji,
     * która chcemy stworzyć
     */
    void createTable(Class<?> tClass);

    /**
     * @param o - obiekt, który chcemy
     * umieścić w bazie danych
     * @return - id obiektu, pod którym
     * jest zapisany w bazie danych
     */
    Object insert(Object o);

    /**
     * @param o - zaaktualizowany obiekt,
     * który chcemy zaaktualizować
     * w bazie danych
     * @return - true/false w zależności,
     * czy udało się zaaktualizować obiekt
     */
    boolean update(Object o);

    /**
     * @param tClass - typ klasy obiektu, ktory chcemy wyciagac
     * @param id - id obiektu, ktory chcemy wyciagnac
     * @return obiekt wyciagniety z bazy danych
     */
    Object select(Class<?> tClass, Object id);

    /**
     * @param o - obiekt, ktrory chcemy usunac
     * @return true/false w zaleznosci czy udalo
     * sie usunac czy nie
     */
    boolean remove(Object o);
}

/*
// TODO: 19.02.19 docelowa implementacja
pl.nask.agent.common.core.domain.IEntity;

    void createTable(Class<? extends IEntity> tClass);
    int insert(IEntity o);
    boolean update(IEntity o, int id);
    IEntity select(Class<? extends IEntity> tClass, int id);

*/
