package javadocq.indiflow.DTO;

import java.time.LocalDateTime;
import javadocq.indiflow.domain.SubTask;
import lombok.Getter;

@Getter
public class SubTaskDTO {
    private final Long id;
    private final String name;
    private final String content;
    private final LocalDateTime date;

    public SubTaskDTO(SubTask subTask) {
        this.id = subTask.getId();
        this.name = subTask.getName();
        this.content = subTask.getContent();
        this.date = subTask.getDate();
    }
}
