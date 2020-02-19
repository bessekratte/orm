package orm.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.nask.agent.component.api.database.domain.IEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MojaKlasaTestowa implements IEntity {

    private String name;
    private String lastName;
    private int age;
}