package orm.persistent.mappers;

public class StringMapper implements PersistentMapper<String, String> {

    private StringMapper(){}

    private static final PersistentMapper instance;

    static {
        instance = new StringMapper();
    }

    public static PersistentMapper getInstance(){
        return instance;
    }

    @Override
    public String convertToDatabaseColumn(String string) {
        return string;
    }

    @Override
    public String convertToJavaClass(String string) {
        return string;
    }

}
