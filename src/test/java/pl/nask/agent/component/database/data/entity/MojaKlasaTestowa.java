package pl.nask.agent.component.database.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.nask.agent.common.core.domain.AbstractDatabaseEntry;
import pl.nask.agent.common.core.domain.IEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MojaKlasaTestowa extends AbstractDatabaseEntry implements IEntity {

    private String name;
    private String lastName;
    private int age;
}