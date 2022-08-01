package works.buddy.library.dao;

import java.util.List;

public interface GenericDAO<T> {

    void save(final T entity);

    void update(final T entity);

    void delete(final T entity);

    T findOne(final long id);

    List<T> findAll();
}
