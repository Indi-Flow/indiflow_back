package javadocq.indiflow.DTO;

import java.time.LocalDateTime;
import javadocq.indiflow.domain.Task;
import lombok.Getter;

@Getter
public class TaskDTO {
    private final Long id;
    private final String name;
    private final String content;
    private final LocalDateTime date;

    public TaskDTO(Task task) {
        this.id = task.getId();
        this.name = task.getName();
        this.content = task.getContent();
        this.date = task.getDate();
    }
}
