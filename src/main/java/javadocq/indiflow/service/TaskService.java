package javadocq.indiflow.service;

import java.util.List;
import javadocq.indiflow.domain.Project;
import javadocq.indiflow.domain.SubTask;
import javadocq.indiflow.domain.Task;
import javadocq.indiflow.repository.ProjectRepository;
import javadocq.indiflow.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    @Transactional
    public Long join(Task task) {
        taskRepository.save(task);
        return task.getId();
    }

    @Transactional
    public Task getTask(Long ProjectId, Long TaskId) {
        Project project = projectRepository.findById(ProjectId);
        if (project == null) {
            throw new IllegalStateException("task not found");
        }
        return taskRepository.findById(TaskId);
    }

    @Transactional
    public List<SubTask> getSubTasks(Long projectId) {
        Task task = taskRepository.findById(projectId);
        if(task == null) {
            throw  new IllegalStateException("task not found");
        }
        return task.getSubTaskList();
    }
    @Transactional
    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }

}
