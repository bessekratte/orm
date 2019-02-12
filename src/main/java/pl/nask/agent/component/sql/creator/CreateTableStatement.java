package pl.nask.agent.component.sql.creator;

import pl.nask.agent.component.reflection.ClassReflectionDispatcher;
import pl.nask.agent.component.exception.UnsupportedSqlTypeException;

import java.util.ArrayList;
import java.util.List;

public class CreateTableStatement {

    public static String getCreateTableSQL(Class clazz) {

        String tableName = clazz.getSimpleName().toLowerCase();
        List<String> types = ClassReflectionDispatcher.getListOfClassTypes(clazz);
        List<String> names = ClassReflectionDispatcher.getListOfFieldNames(clazz);
        types = convertJavaTypesToSqlTypes(types);
        return CreateTableStatement.buildCreateSQL(tableName, types, names);
    }

    private static String buildCreateSQL(String tableName, List<String> sqlTypes, List<String> fieldNames) {

        StringBuilder sql = new StringBuilder("CREATE TABLE IF NOT EXISTS " + tableName + " (");
        sql.append("id INTEGER PRIMARY KEY, ");
        for (int i = 0; i < fieldNames.size(); i++) {
            sql.append(fieldNames.get(i))
                    .append(" ")
                    .append(sqlTypes.get(i))
                    .append(", ");
        }

        sql.delete(sql.length() - 2, sql.length()); // usuniecie ostatniego ", " ktory nie oddziela juz kolejnego typu
        sql.append(");");
        return sql.toString();
    }

    private static List<String> convertJavaTypesToSqlTypes(List<String> javaTypes) {

        List<String> sqlTypes = new ArrayList<>();

        for (int i = 0; i < javaTypes.size(); i++) {

            switch (javaTypes.get(i)){

                case "String":{
                    sqlTypes.add("VARCHAR");
                    break;
                }
                case "int":{
                    sqlTypes.add("INTEGER");
                    break;
                }
                case "Integer":{
                    sqlTypes.add("INTEGER");
                    break;
                }
                case "LocalDateTime":{
                    sqlTypes.add("TIMESTAMP");
                    break;
                }
                default:
                    throw new UnsupportedSqlTypeException();
            }
        }
        return sqlTypes;
    }
}

