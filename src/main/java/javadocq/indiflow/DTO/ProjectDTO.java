package javadocq.indiflow.DTO;

import java.time.LocalDateTime;
import javadocq.indiflow.domain.Project;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class ProjectDTO {
    private final Long id;
    private final String name;
    private final String content;
    private final LocalDateTime date;

    public ProjectDTO(Project project) {
        this.id = project.getId();
        this.name = project.getName();
        this.content = project.getContent();
        this.date = project.getDate();
    }

}
