package works.buddy.library.api.services;

import works.buddy.library.api.view.BookFront;
import works.buddy.library.api.view.BooksFront;

public interface BookService {

    BooksFront getBooks();

    BookFront getBook(String id);

    BookFront createBook(BookFront book);

    BookFront updateBook(String bookId, BookFront book);
}
