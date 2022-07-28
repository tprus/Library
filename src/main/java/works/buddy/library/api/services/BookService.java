package works.buddy.library.api.services;

import works.buddy.library.api.view.BookFront;

import java.util.Collection;

public interface BookService {

    Collection<BookFront> getBooks();
    BookFront getBook(Long id);

    void createBook(BookFront book);
}
