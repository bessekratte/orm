package orm.impl;

import orm.properties.PropertiesResolver;
import orm.sql.creator.*;
import orm.sql.executors.*;
import pl.nask.agent.component.api.database.ISharedDatabase;
import pl.nask.agent.component.api.database.domain.IEntity;
import pl.nask.agent.component.database.sql.creator.*;
import pl.nask.agent.component.database.sql.executors.*;

import java.lang.reflect.ParameterizedType;

/*
 * Przykład użycia:
 *
 * Tworzymy instancje implementacji bazy danych
 * ISharedDatabase db = new SharedDatabaseImpl();

 * Tworzymy obiekt encji ktory chcemy zapisac w bazie danych (kazdy obiekt encji musi implementowac interfejs IEnity)
 * SomeEntity object = new SomeEntity("Jan", "Kowalski", 56);
 *
 * Tworzymy tabele w bazie danych dla naszej encjii
 * db.createTable(SomeEntity.class);

 * Insertujemy nasz obiekt do bazy danych, dostajac w zamian id pod ktorym bedzie sie ukrywal obiekt w bazie danych
 * int id = db.insert(object);

 * Wydobywamy z bazy danych nasz obiekt, podajac klase obiektu oraz jego id
 * object = db.select(SomeEntity.class, id);

 * Aktualizujemy pola obiektu
 * object.setAge(57);

 * Aktualizujemy w bazie danych obiekt
 * db.update(object);
 */

public abstract class SharedDatabaseImpl<T extends IEntity> implements ISharedDatabase<T> {

    private static final String DATABASE_URL;
    private static final String DATABASE_USER;
    private static final String DATABASE_PASSWORD;

    private Class<T> genericClass;

    static {
        DATABASE_URL = PropertiesResolver.getProperty("database.url");
        DATABASE_USER = PropertiesResolver.getProperty("database.user");
        DATABASE_PASSWORD = PropertiesResolver.getProperty("database.password");
    }

    public SharedDatabaseImpl(){
        this.genericClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
        createTable();
    }

    private void createTable() {
        String sql = CreateTableStatement.buildCreateTableSQL(genericClass);
        CreateTableExecutor.executeCreateTable(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD, sql);
    }

    @Override
    public Object insert(T o) {
        String sql = InsertStatement.getInsertSQL(o);
        return InsertExecutor.executeInsert(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD, sql);
    }

    @Override
    public boolean update(T o) {
        String sql = UpdateStatement.getUpdateSQL(o);
        return UpdateExecutor.executeUpdate(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD, sql);
    }

    @Override
    public T select(Object id) {
        String sql = SelectStatement.getSelectSQL(genericClass, id);
        return SelectExecutor.executeSelect(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD, sql, genericClass);
    }

    @Override
    public boolean remove(T o) {
        String sql = RemoveStatement.getDeleteSql(o);
        return RemoveExecutor.executeRemove(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD, sql);
    }
}