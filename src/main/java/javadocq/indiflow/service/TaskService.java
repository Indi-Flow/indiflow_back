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
    public Long join(Long projectId, Task task) {
        Project project = projectRepository.findById(projectId);
        task.setProject(project);
        taskRepository.save(task);
        return task.getId();
    }

    @Transactional
    public Task getTask(Long ProjectId, Long taskId) {
        Project project = projectRepository.findById(ProjectId);
        if (project == null) {
            throw new IllegalStateException("task not found");
        }
        Task task = taskRepository.findById(taskId);
        if (task == null || !task.getProject().equals(project)) {
            throw new IllegalStateException("Task not found in this project");
        }

        return task;
    }

    @Transactional
    public List<SubTask> getSubTasks(Long projectId, Long taskId) {
        Task task = taskRepository.findById(taskId);
        if(task == null) {
            throw  new IllegalStateException("task not found");
        }

        if (!task.getProject().getId().equals(projectId)) {
            throw new IllegalStateException("Task does not belong to this project");
        }

        return task.getSubTaskList();
    }
    @Transactional
    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }

}
