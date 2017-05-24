package net.ziemers.swxercise.db.dao;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
// import javax.persistence.criteria.Predicate;
// import javax.persistence.criteria.Root;

import net.ziemers.swxercise.db.BaseEntity;

@Stateless
public class GenericDao {

    @Inject
    private EntityManager entityManager;

    /**
     * Speichert die gegebene Entität.
     *
     * @see EntityManager#persist(Object)
     * @param entity die zu speichernde Entität
     * @param <T> Generic für den Entity-Typ.
     * @return die Id der persistierten Entität
     */
    public <T extends BaseEntity> Long save(final T entity) {
        entityManager.persist(entity);
        return entity.getId();
    }

    public <T extends BaseEntity> T saveOrUpdate(final T entity) {
        return entityManager.merge(entity);
    }

    /**
     * @param entityType Entity-Typ.
     * @param primaryKey Id
     * @param <T> Generic für Entity-Typ
     * @return die Entität mit gegebener Id oder <code>null</code>, wenn keine gefunden
     */
    public <T extends BaseEntity> T findById(Class<T> entityType, Long primaryKey) {
        T entity = entityManager.find(entityType, primaryKey);
        return entity;
    }

    /**
     * @param entityType Entity-Typ.
     * @param <T> Generic für den Entity-Typ.
     * @return alle (aktiven) Entitäten vom gegebenen Typ
     */
    public <T extends BaseEntity> Collection<T> findAll(Class<T> entityType) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = cb.createQuery(entityType);
        // Root<T> root = query.from(entityType);
        // Predicate activeStatus = cb.equal(root.get(STATUS), Status.ACTIVE);
        // query.where(activeStatus);
        return entityManager.createQuery(query).getResultList();
    }

}
