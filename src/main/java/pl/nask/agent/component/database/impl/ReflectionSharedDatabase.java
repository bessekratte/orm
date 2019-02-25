package pl.nask.agent.component.database.impl;

import pl.nask.agent.component.database.sql.creator.InsertStatement;
import pl.nask.agent.component.database.sql.creator.SelectStatement;
import pl.nask.agent.component.database.sql.creator.UpdateStatement;
import pl.nask.agent.component.database.sql.creator.CreateTableStatementWithAnnotations;
import pl.nask.agent.component.database.sql.executors.CreateTableExecutor;
import pl.nask.agent.component.database.sql.executors.InsertExecutor;
import pl.nask.agent.component.database.sql.executors.SelectExecutor;
import pl.nask.agent.component.database.ISharedDatabase;
import pl.nask.agent.component.database.sql.executors.UpdateExecutor;

public class ReflectionSharedDatabase implements ISharedDatabase {

    @Override
    public void createTable(Class<?> tClass) {
        String sql = CreateTableStatementWithAnnotations.buildCreateTableSQL(tClass);
        CreateTableExecutor.executeCreateTable(ISharedDatabase.DATABASE_URL, ISharedDatabase.DATABASE_USER, ISharedDatabase.DATABASE_PASSWORD, sql);
    }

    @Override
    public Object insert(Object o) {
        String sql = InsertStatement.getInsertSQL(o);
        return InsertExecutor.executeInsert(ISharedDatabase.DATABASE_URL, ISharedDatabase.DATABASE_USER, ISharedDatabase.DATABASE_PASSWORD, sql);
    }

    @Override
    public boolean update(Object o) {
        String sql = UpdateStatement.getUpdateSQL(o);
        return UpdateExecutor.executeUpdate(ISharedDatabase.DATABASE_URL, ISharedDatabase.DATABASE_USER, ISharedDatabase.DATABASE_PASSWORD, sql);
    }

    @Override
    public Object select(Class<?> tClass, Object id) {
        String sql = SelectStatement.getSelectSQL(tClass, id);
        return SelectExecutor.executeSelect(ISharedDatabase.DATABASE_URL, ISharedDatabase.DATABASE_USER, ISharedDatabase.DATABASE_PASSWORD, sql, tClass);
    }
}
