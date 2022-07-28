package works.buddy.library.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public abstract class AbstractHibernateDAO<T extends Serializable> {

    protected DetachedCriteria createCriteria() {
        return DetachedCriteria.forClass(getEntityClass());
    }

    @Autowired
    protected SessionFactory sessionFactory;

    protected abstract Class<T> getEntityClass();

    public T findOne(final long id) {
        return (T) getCurrentSession().get(getEntityClass(), id);
    }

    protected Collection<T> find(DetachedCriteria criteria) {
        return criteria.getExecutableCriteria(getCurrentSession()).list();
    }

    protected T findOne(DetachedCriteria criteria) {
        return (T) criteria.getExecutableCriteria(getCurrentSession()).uniqueResult();
    }

    public List<T> findAll() {
        return getCurrentSession().createQuery("from " + getEntityClass().getName()).list();
    }

    public T create(final T entity) {
        getCurrentSession().saveOrUpdate(entity);
        return entity;
    }

    public T update(final T entity) {
        return (T) getCurrentSession().merge(entity);
    }

    public void delete(final T entity) {
        getCurrentSession().delete(entity);
    }

    public void deleteById(final long entityId) {
        final T entity = findOne(entityId);
        delete(entity);
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
