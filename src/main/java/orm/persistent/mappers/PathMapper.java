package orm.persistent.mappers;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathMapper implements PersistentMapper<Path, String>{

    private static final PersistentMapper instance;

    static {
        instance = new PathMapper();
    }

    public static PersistentMapper getInstance(){
        return instance;
    }

    @Override
    public String convertToDatabaseColumn(Path path) {
        return path.toString();
    }

    @Override
    public Path convertToJavaClass(String s) {
        return Paths.get(s);
    }

    private PathMapper() {
    }
}
