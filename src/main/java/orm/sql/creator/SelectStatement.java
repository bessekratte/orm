package orm.sql.creator;


import orm.reflection.ReflectedAnnotations;

public class SelectStatement {

    public static String getSelectSQL(Class<?> clazz, Object id) {

        String tableName = clazz.getSimpleName().toLowerCase();
        String idFieldName = ReflectedAnnotations.getFieldBeingId(clazz).getName();
        return "SELECT * FROM " + tableName + " WHERE " +  idFieldName + " = " + "\""  + id + "\"";
    }
}
