package pl.nask.agent.component.database.persistent;

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
                                        // TODO: 07.03.19 zmienic klase wyjatku
                                        .orElseThrow(RuntimeException::new)
                                        .getMapper()
                                        .convertToDatabaseColumn(entrySet.getValue())));
    }
}
