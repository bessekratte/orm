package pl.nask.agent.component.database.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Transient;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class MojaKlasaTestowaGdzieIdToString {

    @Id
    private String id;
    private String name;
    @Transient
    private int age;
    private LocalDateTime creationTime;
}