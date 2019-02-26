package pl.nask.agent.component.database.impl;

import pl.nask.agent.component.database.resolver.PropertiesResolver;
import pl.nask.agent.component.database.sql.creator.InsertStatement;
import pl.nask.agent.component.database.sql.creator.SelectStatement;
import pl.nask.agent.component.database.sql.creator.UpdateStatement;
import pl.nask.agent.component.database.sql.creator.CreateTableStatementWithAnnotations;
import pl.nask.agent.component.database.sql.executors.CreateTableExecutor;
import pl.nask.agent.component.database.sql.executors.InsertExecutor;
import pl.nask.agent.component.database.sql.executors.SelectExecutor;
import pl.nask.agent.component.database.ISharedDatabase;
import pl.nask.agent.component.database.sql.executors.UpdateExecutor;

import java.nio.file.Paths;

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
    public void createTable(Class<?> tClass) {
        System.out.println(Paths.get("").toAbsolutePath());
        String sql = CreateTableStatementWithAnnotations.buildCreateTableSQL(tClass);
        CreateTableExecutor.executeCreateTable(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD, sql);
    }

    @Override
    public Object insert(Object o) {
        String sql = InsertStatement.getInsertSQL(o);
        return InsertExecutor.executeInsert(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD, sql);
    }

    @Override
    public boolean update(Object o) {
        String sql = UpdateStatement.getUpdateSQL(o);
        return UpdateExecutor.executeUpdate(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD, sql);
    }

    @Override
    public Object select(Class<?> tClass, Object id) {
        String sql = SelectStatement.getSelectSQL(tClass, id);
        return SelectExecutor.executeSelect(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD, sql, tClass);
    }
}
