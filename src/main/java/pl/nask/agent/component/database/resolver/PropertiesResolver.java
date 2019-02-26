package pl.nask.agent.component.database.resolver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;

public class PropertiesResolver {

    private PropertiesResolver(){
    }

    private final static Map<String, String> properties;

    static {
        properties = loadProperties();
    }

    private static Map<String, String> loadProperties() {
        Path properties = Paths.get("src/main/resources/application.properties");
        try {
            return Files.lines(properties).collect(Collectors.toMap(
                    (String key) -> key.substring(0, key.indexOf("=")).trim(), // tworzenie klucza
                    (String value) -> value.substring(value.indexOf("=") + 1).trim(), // tworzenie wartosci
                    (valueFirst, valueSecond) -> valueFirst + ", " + valueSecond)); // co ma zrobic w wypadku kiedy jeden klucz ma kilka wartosci
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("there's no properties file in directory: src/main/resources");
        }
    }

    public static String getProperty(String key){
        String prop = properties.get(key);
        if (prop == null)
            throw new RuntimeException("there's no [" + "] property in properties");

        return prop;
    }


}
