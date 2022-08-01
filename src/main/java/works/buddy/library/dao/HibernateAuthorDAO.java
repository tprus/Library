package works.buddy.library.dao;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import works.buddy.library.api.view.AuthorFront;
import works.buddy.library.model.Author;

import java.util.Collection;

@Repository
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
        return findOne(criteria);
    }

    @Override
    public Collection<Author> getAuthors() {
        return findAll();
    }

    @Override
    public Author GetAuthor(Long authorId) {
        return findOne(authorId);
    }
}
