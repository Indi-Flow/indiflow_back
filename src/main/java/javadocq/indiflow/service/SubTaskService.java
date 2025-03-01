package javadocq.indiflow.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import javadocq.indiflow.domain.SubTask;
import javadocq.indiflow.repository.SubTaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SubTaskService {
    private final SubTaskRepository subTaskRepository;

    @Transactional
    public SubTask save(SubTask subTask) {
        subTaskRepository.save(subTask);
        return subTask;
    }

    @Transactional
    public void delete(Long subTaskId) {
        subTaskRepository.deleteById(subTaskId);
    }
}
