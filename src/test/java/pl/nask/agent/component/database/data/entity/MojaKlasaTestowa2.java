package pl.nask.agent.component.database.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Transient;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class MojaKlasaTestowa2 {

    @Id
    private int id;
    @Transient
    private String NaS;
    private String name;
    private String lastName;

    public MojaKlasaTestowa2(String naS, String name, String lastName) {
        NaS = naS;
        this.name = name;
        this.lastName = lastName;
    }
}
