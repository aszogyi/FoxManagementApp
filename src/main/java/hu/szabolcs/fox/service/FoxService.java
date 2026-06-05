package hu.szabolcs.fox.service;

import hu.szabolcs.fox.domain.Fox;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
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
        System.out.println("Deleting fox with id: " + id);
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

    public void update(Fox fox) {
        entityManager.merge(fox);
    }

    public Fox findById(Long id) {
        return entityManager.find(Fox.class, id);
    }

    public Fox findOneWithoutImage() {
        List<Fox> foxes = entityManager
                .createQuery(
                        "SELECT f FROM Fox f WHERE f.imageUrl IS NULL OR f.imageUrl = ''",
                        Fox.class
                )
                .setMaxResults(1)
                .getResultList();

        return foxes.isEmpty() ? null : foxes.get(0);
    }
}
