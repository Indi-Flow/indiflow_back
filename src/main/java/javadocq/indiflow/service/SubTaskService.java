package javadocq.indiflow.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import javadocq.indiflow.domain.SubTask;
import javadocq.indiflow.domain.Task;
import javadocq.indiflow.repository.SubTaskRepository;
import javadocq.indiflow.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SubTaskService {
    private final SubTaskRepository subTaskRepository;
    private final TaskRepository taskRepository;

    @Transactional
    public SubTask save(SubTask subTask, Long taskId) {
        Task task = taskRepository.findById(taskId);
        subTask.setTask(task);
        subTaskRepository.save(subTask);
        return subTask;
    }

    @Transactional
    public void delete(Long subTaskId) {
        SubTask subTask = subTaskRepository.findById(subTaskId);
        if(subTask.getTask() != null) {
            subTaskRepository.deleteById(subTaskId);
        }  else  {
            throw new IllegalStateException("SubTask does not exist");
        }

    }
}
