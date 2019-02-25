package pl.nask.agent.component.database.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.nask.agent.common.core.domain.AbstractDatabaseEntry;
import pl.nask.agent.common.core.domain.IEntity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CzasowaKlasaTestowa extends AbstractDatabaseEntry implements IEntity {

    private Timestamp timestamp;
    private LocalDateTime localDateTime;
}
