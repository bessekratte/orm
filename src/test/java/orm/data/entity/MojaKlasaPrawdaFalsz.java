package orm.data.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.nask.agent.component.api.database.domain.IEntity;

import javax.persistence.Id;

@Data
@NoArgsConstructor
public class MojaKlasaPrawdaFalsz implements IEntity {

    @Id
    private int id;
    private Boolean asd;
    private boolean dsa;

    public MojaKlasaPrawdaFalsz(Boolean a, boolean b) {
        this.asd = a;
        this.dsa = b;
    }

}
