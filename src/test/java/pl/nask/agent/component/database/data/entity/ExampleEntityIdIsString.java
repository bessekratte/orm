package pl.nask.agent.component.database.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Transient;
import java.nio.file.Path;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExampleEntityIdIsString {

    @Id
    private String identify;
    private int age;
    private Integer doubledAge;
    private String sampleTest;
    private LocalDateTime creationTime;
    @Transient
    private Path path;
}
