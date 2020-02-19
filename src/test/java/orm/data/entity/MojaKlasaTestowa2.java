package orm.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.nask.agent.component.api.database.domain.IEntity;

import javax.persistence.Id;
import javax.persistence.Transient;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class MojaKlasaTestowa2 implements IEntity {

    @Id
    private int id;
    @Transient
    private String NaS;
    private String name;
    private String lastName;

    public MojaKlasaTestowa2(String naS, String name, String lastName) {
        this.NaS = naS;
        this.name = name;
        this.lastName = lastName;
    }
}
