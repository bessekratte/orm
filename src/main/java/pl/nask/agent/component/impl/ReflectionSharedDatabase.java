package pl.nask.agent.component.impl;

import pl.nask.agent.component.ISharedDatabase;
import pl.nask.agent.component.sql.creator.CreateTableStatement;
import pl.nask.agent.component.sql.creator.InsertStatement;
import pl.nask.agent.component.sql.creator.SelectStatement;
import pl.nask.agent.component.sql.executors.CreateTableExecutor;
import pl.nask.agent.component.sql.executors.InsertExecutor;
import pl.nask.agent.component.sql.executors.SelectExecutor;

public class ReflectionSharedDatabase<T> implements ISharedDatabase<T> {

    @Override
    public void createTable(Class<T> tClass) {
        Class<T> tClass1;
        Class<?> tclass1;
        String sql = CreateTableStatement.getCreateTableSQL(tClass);
        CreateTableExecutor.executeCreateTable(ISharedDatabase.DATABASE_URL, sql);
    }

    @Override
    public int insert(T o) {
        String sql = InsertStatement.getInsertObjectSQL(o);
        return InsertExecutor.executeInsert(ISharedDatabase.DATABASE_URL, sql);
    }

    @Override
    public boolean update(Object o, int id) {
        return false;
    }

    @Override
    public T select(Class<T> clazz, int id) {
        String sql = SelectStatement.buildSelectStatement(clazz.getSimpleName().toLowerCase(), id);
        return (T) SelectExecutor.executeSelect(ISharedDatabase.DATABASE_URL, sql, clazz);
    }
}
