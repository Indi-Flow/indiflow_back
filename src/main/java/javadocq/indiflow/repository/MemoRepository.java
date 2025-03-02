package javadocq.indiflow.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import javadocq.indiflow.domain.Memo;
import org.springframework.stereotype.Repository;

@Repository
public class MemoRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Memo memo) {
        em.persist(memo);
    }

    public Memo findById(Long id) {
        return em.find(Memo.class, id);
    }
}
