package works.buddy.library.dao;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import works.buddy.library.model.Author;

@Repository
@Component("hibernateAuthorDAO")
public class HibernateAuthorDAO extends AbstractHibernateDAO<Author> implements AuthorDAO {

    private static final String FIRST_NAME = "firstName";

    private static final String LAST_NAME = "lastName";

    @Override
    protected Class<Author> getEntityClass() {
        return Author.class;
    }

    @Override
    public Author getAuthorByFullName(String firstName, String lastName) {
        DetachedCriteria criteria = createCriteria();
        criteria.add(Restrictions.eq(FIRST_NAME, firstName));
        criteria.add(Restrictions.eq(LAST_NAME, lastName));
        return find(criteria).iterator().next();
    }
}
