package pl.nask.agent.component.database.impl;

import pl.nask.agent.common.core.domain.IEntity;
import pl.nask.agent.component.database.sql.creator.CreateTableStatement;
import pl.nask.agent.component.database.sql.creator.InsertStatement;
import pl.nask.agent.component.database.sql.creator.SelectStatement;
import pl.nask.agent.component.database.sql.executors.CreateTableExecutor;
import pl.nask.agent.component.database.sql.executors.InsertExecutor;
import pl.nask.agent.component.database.sql.executors.SelectExecutor;
import pl.nask.agent.component.database.sql.creator.UpdateStatement;
import pl.nask.agent.component.database.ISharedDatabase;

public class ReflectionSharedDatabase implements ISharedDatabase {

    @Override
    public void createTable(Class<? extends IEntity> tClass) {
        String sql = CreateTableStatement.getCreateTableSQL(tClass);
        CreateTableExecutor.executeCreateTable(ISharedDatabase.DATABASE_URL, ISharedDatabase.DATABASE_USER, ISharedDatabase.DATABASE_PASSWORD, sql);
    }

    @Override
    public int insert(IEntity o) {
        String sql = InsertStatement.getInsertSQL(o);
        return InsertExecutor.executeInsert(ISharedDatabase.DATABASE_URL, ISharedDatabase.DATABASE_USER, ISharedDatabase.DATABASE_PASSWORD, sql);
    }

    @Override
    public boolean update(IEntity o, int id) {
//        String sql = UpdateStatement.getUpdateSQL(o, id);
//        return UpdateExecutor.executeUpdate(ISharedDatabase.DATABASE_URL, ISharedDatabase.DATABASE_USER, ISharedDatabase.DATABASE_PASSWORD, sql);
        return false;
    }

    @Override
    public IEntity select(Class<? extends IEntity> tClass, int id) {
        String sql = SelectStatement.getSelectSQL(tClass.getSimpleName().toLowerCase(), id);
//        return SelectExecutor.executeSelect(ISharedDatabase.DATABASE_URL, ISharedDatabase.DATABASE_USER, ISharedDatabase.DATABASE_PASSWORD, sql, tClass);
        return null;
    }
}
