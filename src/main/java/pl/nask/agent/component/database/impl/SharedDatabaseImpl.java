package pl.nask.agent.component.database.impl;

import pl.nask.agent.component.api.database.domain.IEntity;
import pl.nask.agent.component.database.properties.PropertiesResolver;
import pl.nask.agent.component.database.sql.creator.*;
import pl.nask.agent.component.database.sql.executors.*;
import pl.nask.agent.component.api.database.ISharedDatabase;

/**
 * Przykład użycia:
 *
 * public class SomeClass {
 *
 *     public static void main(String[] args) {
 *
 *          //tworzymy instancje implementacji bazy danych
 *          ISharedDatabase db = new SharedDatabaseImpl();
 *
 *          // Tworzymy obiekt encji ktory chcemy zapisac w bazie danych (kazdy obiekt encji musi implementowac interfejs IEnity)
 *          SomeEntity object = new SomeEntity("Jan", "Kowalski", 56);
 *
 *          // Tworzymy tabele w bazie danych dla naszej encjii
 *          db.createTable(SomeEntity.class);
 *
 *          // Insertujemy nasz obiekt do bazy danych, dostajac w zamian id pod ktorym bedzie sie ukrywal obiekt w bazie danych
 *          int id = db.insert(object);
 *
 *          // Wydobywamy z bazy danych nasz obiekt, podajac klase obiektu oraz jego id
 *          object = db.select(SomeEntity.class, id);
 *
 *          // Aktualizujemy pola obiektu
 *          object.setAge(57);
 *
 *          // Aktualizujemy w bazie danych obiekt
 *          db.update(object);
 *
 *     }
 * }
 */

public class SharedDatabaseImpl implements ISharedDatabase {

    private static final String DATABASE_URL;
    private static final String DATABASE_USER;
    private static final String DATABASE_PASSWORD;

    static {
        DATABASE_URL = PropertiesResolver.getProperty("database.url");
        DATABASE_USER = PropertiesResolver.getProperty("database.user");
        DATABASE_PASSWORD = PropertiesResolver.getProperty("database.password");
    }

    @Override
    public void createTable(Class<? extends IEntity> tClass) {
        String sql = CreateTableStatement.buildCreateTableSQL(tClass);
        CreateTableExecutor.executeCreateTable(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD, sql);
    }

    @Override
    public Object insert(IEntity o) {
        String sql = InsertStatement.getInsertSQL(o);
        return InsertExecutor.executeInsert(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD, sql);
    }

    @Override
    public boolean update(IEntity o) {
        String sql = UpdateStatement.getUpdateSQL(o);
        return UpdateExecutor.executeUpdate(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD, sql);
    }

    @Override
    public IEntity select(Class<? extends IEntity> tClass, Object id) {
        String sql = SelectStatement.getSelectSQL(tClass, id);
        return (IEntity) SelectExecutor.executeSelect(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD, sql, tClass);
    }

    @Override
    public boolean remove(IEntity o) {
        String sql = RemoveStatement.getDeleteSql(o);
        return RemoveExecutor.executeRemove(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD, sql);
    }
}