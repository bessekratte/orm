package pl.nask.agent.component.database.sql.creator;

import pl.nask.agent.component.database.reflection.ClassReflectionDispatcher;
import pl.nask.agent.component.database.exception.UnsupportedSqlTypeException;

import java.util.ArrayList;
import java.util.List;

// TODO: 19.02.19 jest nowsza wersja

@Deprecated
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

        for (String javaType : javaTypes) {

            switch (javaType) {

                case "String":
                    sqlTypes.add("VARCHAR");
                    break;

                case "int":
                    sqlTypes.add("INTEGER");
                    break;

                case "Integer":
                    sqlTypes.add("INTEGER");
                    break;

                // TODO: 14.02.19 text against timestamp ?
                case "LocalDateTime":
                    sqlTypes.add("TIMESTAMP");
                    break;

                case "Timestamp":
                    sqlTypes.add("TIMESTAMP");
                    break;

                default:
                    throw new UnsupportedSqlTypeException();
            }
        }
        return sqlTypes;
    }
}

