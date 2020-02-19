package orm.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.nask.agent.component.api.database.domain.IEntity;

import javax.persistence.Id;
import javax.persistence.Transient;
import java.nio.file.Path;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExampleEntityIdIsInt implements IEntity {

    @Id
    private Integer id;
    private int age;
    private Integer doubledAge;
    private String sampleTest;
    private LocalDateTime creationTime;
    @Transient
    private Path path;

    public ExampleEntityIdIsInt(int age, Integer doubledAge, String sampleTest, LocalDateTime creationTime, Path path) {
        this.age = age;
        this.doubledAge = doubledAge;
        this.sampleTest = sampleTest;
        this.creationTime = creationTime;
        this.path = path;
    }
}
