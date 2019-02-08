package pl.nask.agent.component.sql.creator;

import pl.nask.agent.component.object.ClassAtomizer;
import pl.nask.agent.component.object.exception.UnsupportedSqlTypeException;

import java.util.ArrayList;
import java.util.List;

public class CreateTableStatement {

    private static String buildCreateSQL(String tableName, List<String> sqlTypes, List<String> fieldNames) {

        StringBuilder sql = new StringBuilder("CREATE TABLE IF NOT EXISTS " + tableName + " (");
        sql.append("id INTEGER PRIMARY KEY, ");//<-- TODO  !!!!!!!!!!!!!!!!!!!!!!!!!!!!!
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

    public static String getCreateTableSQL(Object obj) {

        String tableName = obj.getClass().getSimpleName().toLowerCase();
        List<String> types = ClassAtomizer.getListOfClassTypes(obj);
        List<String> names = ClassAtomizer.getListOfFieldNames(obj);
        types = convertJavaTypesToSqlTypes(types);
        return CreateTableStatement.buildCreateSQL(tableName, types, names);
    }

    private static List<String> convertJavaTypesToSqlTypes(List<String> javaTypes) {

        List<String> sqlTypes = new ArrayList<>();

        for (int i = 0; i < javaTypes.size(); i++) {

            if (javaTypes.get(i).equals("String")) {
                sqlTypes.add("VARCHAR");
                continue;
            }

            if (javaTypes.get(i).equals("int") ||
                    javaTypes.get(i).equals("Integer")) {
                sqlTypes.add("INTEGER");
                continue;
            }

            throw new UnsupportedSqlTypeException();
        }
        return sqlTypes;
    }
}

