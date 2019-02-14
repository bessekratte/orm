package pl.nask.agent.component.database.mapper.type;

import pl.nask.agent.component.database.mapper.TypeConverter;

public class StringTypeConverter implements TypeConverter {

    @Override
    public String convertTypeToSqlType() {
        return "VARCHAR";
    }

}
