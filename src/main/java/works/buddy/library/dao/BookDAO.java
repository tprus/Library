package works.buddy.library.dao;

import works.buddy.library.model.Author;
import works.buddy.library.model.Book;

import java.util.Collection;

public interface BookDAO extends GenericDAO<Book> {

    Collection<Book> findByAuthorId(Long authorId);

    Collection<Book> findByTitle(String title);

    Collection<Book> findByAuthor(Author author);
}
