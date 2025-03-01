package javadocq.indiflow.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import javadocq.indiflow.domain.Project;
import javadocq.indiflow.domain.Task;
import org.springframework.stereotype.Repository;

@Repository
public class ProjectRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Project project) {
        em.persist(project);
    }

    public Project findById(Long id) {
        return em.find(Project.class, id);
    }

    public void deleteById(Long id) {
        Project project = em.find(Project.class, id);
        em.remove(project);
    }
}
