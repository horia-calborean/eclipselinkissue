package ro.calborean.eclipselinkrecursiveissue.ws.repo;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.eclipse.persistence.jpa.JpaHelper;
import org.eclipse.persistence.queries.QueryByExamplePolicy;
import org.eclipse.persistence.queries.ReadAllQuery;

public abstract class CrudRepoBean<T> {

    @PersistenceContext(unitName = "lib/ondemand-domain.jar#ondemand-db")
    private EntityManager manager;

    protected CrudRepoBean() {
    }

    protected void flushClear() {
        manager.flush();
        manager.clear();
    }

    public List<T> find(T entity) {

        ReadAllQuery q = readAllQuery(entity);
        try {
            Query query = JpaHelper.createQuery(q, manager);
            return query.getResultList();
        } catch (NoResultException e) {
            return new ArrayList<>(0);
        }
    }

    private QueryByExamplePolicy policy() {
        QueryByExamplePolicy policy = new QueryByExamplePolicy();
        policy.excludeDefaultPrimitiveValues();
        policy.addSpecialOperation(String.class, "like");
        return policy;
    }

    private ReadAllQuery readAllQuery(T entity) {

        QueryByExamplePolicy policy = policy();
        ReadAllQuery q = new ReadAllQuery();
        q.setExampleObject(entity);
        q.setQueryByExamplePolicy(policy);
        return q;

    }
}
