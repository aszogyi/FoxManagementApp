package hu.szabolcs.fox;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class FoxService {

    @PersistenceContext(unitName = "foxPU")
    private EntityManager entityManager;

    public void save(Fox fox) {
        entityManager.persist(fox);
    }

    public List<Fox> findAll() {
        return entityManager
                .createQuery("SELECT f FROM Fox f", Fox.class)
                .getResultList();
    }

    public void delete(Long id) {
        Fox fox = entityManager.find(Fox.class, id);

        if (fox != null) {
            entityManager.remove(fox);
        }
    }

    public void deleteAll() {
        entityManager
                .createQuery("DELETE FROM Fox")
                .executeUpdate();
    }
}
