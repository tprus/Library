package works.buddy.library.dao;

import works.buddy.library.model.Book;

import java.util.Collection;

public interface BookDAO {

    void save(Book book);

    Collection<Book> getBooks();
}
