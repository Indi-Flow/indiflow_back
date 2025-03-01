package javadocq.indiflow.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import javadocq.indiflow.domain.SubTask;
import javadocq.indiflow.domain.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SubTaskRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(SubTask subTask) {
        em.persist(subTask);
    }

    public SubTask findById(Long id) {
        return em.find(SubTask.class, id);
    }

    public void deleteById(Long id) {
        SubTask subTask = em.find(SubTask.class, id);
        em.remove(subTask);
    }
}
