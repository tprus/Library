package works.buddy.library.dao;

import works.buddy.library.model.Author;
import works.buddy.library.model.Book;

import java.util.Collection;

public interface BookDAO {

    void save(Book book);

    Collection<Book> getBooks();

    Collection<Book> getBooksByAuthorId(Integer authorId);

    Collection<Book> getBooksByTitle(String title);

    Collection<Book> getBooksByAuthor(Author author);
}
