    void createTable(Class<?> tClass);
    Object insert(Object o);
    boolean update(Object o);
    Object select(Class<?> tClass, Object id);

    Użycie:

    Aby rozpocząć pracę z
    - pl.nask.agent.component.database.ISharedDatabase

    należy zainicjalizować ten interfejs implementacją 
    - pl.nask.agent.component.database.impl.SharedDatabaseImpl

    ISharedDatabase db = new SharedDatabaseImpl

    należy na samym początku stworzyć tabelę wywoułując metodę createTable z parametrem typu klasy encji
    tj.:
    db.createTable(MojaEncja.class);

    Wymagania co do encji:

    Encja może posiadać typy zadeklarowane w enumie
    pl.nask.agent.component.database.persistent.DataType

