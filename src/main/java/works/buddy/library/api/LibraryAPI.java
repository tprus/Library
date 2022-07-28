package works.buddy.library.api;

import works.buddy.library.api.view.BookFront;
import works.buddy.library.api.view.BooksFront;

import javax.ws.rs.core.Response;

public interface LibraryAPI {

    BooksFront getBooks();

    Response createBook(BookFront book);
}
