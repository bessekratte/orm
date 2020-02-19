package orm.persistent;

import java.sql.ResultSet;
import java.util.Map;
import java.util.stream.Collectors;

public class JavaToDatabaseConverter {

    public static Map<String, Object> makeJavaObjectsDatabaseReadable(Map<String, Object> javaObjects) {

        return javaObjects.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entrySet -> SupportedJavaClass.stream()
                                .filter(en -> en.getClassType().equals(entrySet.getValue().getClass()))
                                .findFirst()
                                .orElseThrow(RuntimeException::new)
                                .getMapper()
                                .convertToDatabaseColumn(entrySet.getValue())));
    }

    public static Map<String, Object> makeDatabaseObjectAsJavaObjects(Map<String, Class<?>> databaseObjects, ResultSet rs) {

        return databaseObjects.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        fieldType -> {

                            SupportedJavaClass supportedJavaClass = SupportedJavaClass.stream()
                                    .filter(data -> data.getClassType().equals(fieldType.getValue()))
                                    .findFirst()
                                    .orElseThrow(RuntimeException::new);

                            Object result = supportedJavaClass.getResultsetDispatcher()
                                    .invokeResultSetGetter(rs, fieldType.getKey());

                            result = supportedJavaClass.getMapper()
                                    .convertToJavaClass(result);

                            return result;
                        }));
    }
}