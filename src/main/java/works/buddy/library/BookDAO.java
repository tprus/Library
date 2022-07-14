package works.buddy.library;

import java.util.Collection;

public interface BookDAO {

    void save(Book book);

    Collection<Book> getBooks();
}
