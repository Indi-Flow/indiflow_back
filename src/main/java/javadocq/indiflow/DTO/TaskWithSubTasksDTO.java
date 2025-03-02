package javadocq.indiflow.DTO;

import java.util.List;
import javadocq.indiflow.domain.Task;
import lombok.Getter;

@Getter
public class TaskWithSubTasksDTO {
    private final Long id;
    private final String name;
    private final List<SubTaskDTO> subTasks;

    public TaskWithSubTasksDTO(Task task) {
        this.id = task.getId();
        this.name = task.getName();
        this.subTasks = task.getSubTaskList().stream().map(SubTaskDTO::new).toList();;
    }
}
