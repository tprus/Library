package works.buddy.library.api.services;

import works.buddy.library.api.view.BookFront;
import works.buddy.library.api.view.BooksFront;

public interface BookService {

    BooksFront getBooks();

    BookFront getBook(Integer id);

    BookFront addBook(BookFront book);

    BookFront updateBook(Integer bookId, BookFront book);

    void deleteBook(Integer id);
}
