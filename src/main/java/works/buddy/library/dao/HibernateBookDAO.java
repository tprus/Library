package works.buddy.library.dao;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.stereotype.Repository;
import works.buddy.library.model.Author;
import works.buddy.library.model.Book;

import java.util.Collection;

@Repository
public class HibernateBookDAO extends AbstractHibernateDAO<Book> implements BookDAO {

    private static final String ID = "id";

    private static final String TITLE = "title";

    private static final String AUTHOR = "author";

    private static final String FIRST_NAME = "firstName";

    private static final String LAST_NAME = "lastName";

    @Override
    protected Class<Book> getEntityClass() {
        return Book.class;
    }
    
    @Override
    public Collection<Book> findByAuthorId(Long authorId) {
        DetachedCriteria criteria = createCriteria();
        DetachedCriteria authorCriteria = getAuthorCriteria(criteria);
        authorCriteria.add(Restrictions.eq(ID, authorId));
        return find(criteria);
    }

    @Override
    public Collection<Book> findByTitle(String title) {
        DetachedCriteria criteria = createCriteria();
        criteria.add(getLike(TITLE, title));
        return find(criteria);
    }

    @Override
    public Collection<Book> findByAuthor(Author author) {
        DetachedCriteria criteria = createCriteria();
        DetachedCriteria authorCriteria = getAuthorCriteria(criteria);
        authorCriteria.add(Restrictions.and(getLike(FIRST_NAME, author.getFirstName()), getLike(LAST_NAME, author.getLastName())));
        return find(criteria);
    }

    private DetachedCriteria getAuthorCriteria(DetachedCriteria criteria) {
        return criteria.createCriteria(AUTHOR);
    }

    private SimpleExpression getLike(String name, String value) {
        return Restrictions.like(name, "%" + value + "%");
    }
}