package pl.nask.agent.component.database.sql.creator;


import pl.nask.agent.component.database.reflection.ReflectedAnnotations;

public class SelectStatement {

    public static String getSelectSQL(Class<?> clazz, Object id) {

        String tableName = clazz.getSimpleName().toLowerCase();
        String idFieldName = ReflectedAnnotations.getFieldBeingId(clazz).getName();
        return "SELECT * FROM " + tableName + " WHERE " +  idFieldName + " = " + "\""  + id + "\"";
    }
}
