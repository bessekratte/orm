package pl.nask.agent.component.impl;

import pl.nask.agent.component.ISharedDatabase;
import pl.nask.agent.component.sql.creator.CreateTableStatement;
import pl.nask.agent.component.sql.creator.InsertStatement;
import pl.nask.agent.component.sql.creator.SelectStatement;
import pl.nask.agent.component.sql.executors.CreateTableExecutor;
import pl.nask.agent.component.sql.executors.InsertExecutor;
import pl.nask.agent.component.sql.executors.SelectExecutor;

public class ReflectionSharedDatabase implements ISharedDatabase {

    @Override
    public void createTable(Class<?> tClass) {
        String sql = CreateTableStatement.getCreateTableSQL(tClass);
        CreateTableExecutor.executeCreateTable(ISharedDatabase.DATABASE_URL, ISharedDatabase.DATABASE_USER, ISharedDatabase.DATABASE_PASSWORD, sql);
    }

    @Override
    public int insert(Object o) {
        String sql = InsertStatement.getInsertObjectSQL(o);
        return InsertExecutor.executeInsert(ISharedDatabase.DATABASE_URL, ISharedDatabase.DATABASE_USER, ISharedDatabase.DATABASE_PASSWORD, sql);
    }

    @Override
    public boolean update(Object o, int id) {
        return false;
    }

    @Override
    public Object select(Class<?> clazz, int id) {
        String sql = SelectStatement.buildSelectStatement(clazz.getSimpleName().toLowerCase(), id);
        return SelectExecutor.executeSelect(ISharedDatabase.DATABASE_URL, ISharedDatabase.DATABASE_USER, ISharedDatabase.DATABASE_PASSWORD, sql, clazz);
    }
}
