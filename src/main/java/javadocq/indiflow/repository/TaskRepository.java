package javadocq.indiflow.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import javadocq.indiflow.domain.Task;
import org.springframework.stereotype.Repository;

@Repository
public class TaskRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Task task) {
        em.persist(task);
    }

    public Task findById(Long id) {
        return em.find(Task.class, id);
    }

    public void deleteById(Long id) {
        Task task = em.find(Task.class, id);
        em.remove(task);
    }
}
