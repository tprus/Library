package works.buddy.library.dao;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.stereotype.Repository;
import works.buddy.library.model.Author;
import works.buddy.library.model.Book;

import java.util.ArrayList;
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
    public void save(Book book) {
        create(book);
    }

    @Override
    public Collection<Book> getBooks() {
        ArrayList<Book> sortedBooks = (ArrayList<Book>) findAll();
        sortedBooks.sort((o1, o2) -> Integer.compare(o1.getAuthor().getLastName().compareTo(o2.getAuthor().getLastName()), 0));
        return sortedBooks;
    }

    @Override
    public Collection<Book> getBooksByAuthorId(Long authorId) {
        DetachedCriteria criteria = createCriteria();
        DetachedCriteria authorCriteria = getAuthorCriteria(criteria);
        authorCriteria.add(Restrictions.eq(ID, authorId));
        return find(criteria);
    }

    @Override
    public Collection<Book> getBooksByTitle(String title) {
        DetachedCriteria criteria = createCriteria();
        criteria.add(getLike(TITLE, title));
        return find(criteria);
    }

    @Override
    public Collection<Book> getBooksByAuthor(Author author) {
        DetachedCriteria criteria = createCriteria();
        DetachedCriteria authorCriteria = getAuthorCriteria(criteria);
        authorCriteria.add(Restrictions.and(getLike(FIRST_NAME, author.getFirstName()), getLike(LAST_NAME, author.getLastName())));
        return find(criteria);
    }

    @Override
    public Book getBook(Long id) {
        return findOne(id);
    }

    private DetachedCriteria getAuthorCriteria(DetachedCriteria criteria) {
        return criteria.createCriteria(AUTHOR);
    }

    private SimpleExpression getLike(String name, String value) {
        return Restrictions.like(name, "%" + value + "%");
    }
}