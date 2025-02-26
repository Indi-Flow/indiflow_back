package javadocq.indiflow.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import javadocq.indiflow.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(User user) {
        em.persist(user);
    }

    public User findByUsername(String username) {
        List<User> users = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class).setParameter("username", username).getResultList();
        if (users.isEmpty()) {
            return null;
        }
        return users.getFirst();
    }

}
