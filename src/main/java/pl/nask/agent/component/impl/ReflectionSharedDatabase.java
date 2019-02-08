package pl.nask.agent.component.impl;

import pl.nask.agent.component.ISharedDatabase;
import pl.nask.agent.component.sql.creator.CreateTableStatement;
import pl.nask.agent.component.sql.creator.InsertStatement;
import pl.nask.agent.component.sql.executors.CreateTableExecutor;
import pl.nask.agent.component.sql.executors.InsertExecutor;

public class ReflectionSharedDatabase implements ISharedDatabase {

    @Override
    public void createTable(Object o) {
        String sql = CreateTableStatement.getCreateTableSQL(o);
        CreateTableExecutor.executeCreateTable(ISharedDatabase.DATABASE_URL, sql);
    }

    @Override
    public int insert(Object o) {
        String sql = InsertStatement.getInsertObjectSQL(o);
        return InsertExecutor.executeInsert(ISharedDatabase.DATABASE_URL, sql);
    }

    @Override
    public boolean update(int id, Object o) {
        return false;
    }

    @Override
    public Object select(Class clazz, int id) {
        return null;
    }
}
