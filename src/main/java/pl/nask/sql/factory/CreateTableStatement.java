package pl.nask.sql.factory;

import pl.nask.object.ClassAtomization;
import pl.nask.sql.map.JavaToSqlConverter;

import java.lang.reflect.Field;
import java.util.List;

public class CreateTableStatement {

    private static String buildCreateSQL(String tableName, List<String> fieldTypes, List<String> fieldNames){

        StringBuilder sql = new StringBuilder("CREATE TABLE " + tableName + " (");
        for (int i = 0; i < fieldNames.size(); i++){
            sql.append(fieldNames.get(i))
                    .append(" ")
                    .append(fieldTypes.get(i))
                    .append(", ");
        }

        sql.delete(sql.length() - 2, sql.length());
        // usuniecie ostatniego ", " ktory nie oddziela juz kolejnego typu
        sql.append(");");
        return sql.toString();
    }

    /*public static String getCreateTableSQL(Object obj) {

        String tableName = obj.getClass().getSimpleName().toLowerCase();
        Field[] classFields = ClassAtomization.getClassFields(obj);
        List<String> types = JavaToSqlConverter.convertJavaTypesToSQLtypes(classFields);
        List<String> names = JavaToSqlConverter.getFieldNames(classFields);
        return CreateTableStatement.buildCreateSQL(tableName, types, names);
    }*/
}
