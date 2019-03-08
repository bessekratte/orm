package pl.nask.agent.component.database.data.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Id;

@Data
@NoArgsConstructor
public class MojaKlasaPrawdaFalsz {

    @Id
    private int id;
    private Boolean asd;
    private boolean dsa;

    public MojaKlasaPrawdaFalsz(Boolean a, boolean b) {
        this.asd = a;
        this.dsa = b;
    }

}
