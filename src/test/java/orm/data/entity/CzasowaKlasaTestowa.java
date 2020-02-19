package orm.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.nask.agent.component.api.database.domain.IEntity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CzasowaKlasaTestowa  implements IEntity {

    private Timestamp timestamp;
    private LocalDateTime localDateTime;
}
