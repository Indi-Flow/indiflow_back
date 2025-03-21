package javadocq.indiflow.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import javadocq.indiflow.domain.Pomodoro;
import org.springframework.stereotype.Repository;

@Repository
public class PomodoroRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Pomodoro pomodoro) {
        em.persist(pomodoro);
    }

    public List<Pomodoro> findAllByUsername(String username) {
        return em.createQuery("SELECT p FROM Pomodoro p WHERE p.user.username = :username", Pomodoro.class).setParameter("username", username).getResultList();
    }



}
