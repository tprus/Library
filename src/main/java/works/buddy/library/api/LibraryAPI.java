package works.buddy.library.api;

import works.buddy.library.api.view.AuthorFront;
import works.buddy.library.api.view.BookFront;
import works.buddy.library.api.view.BooksFront;

import javax.ws.rs.core.Response;
import java.util.Collection;

public interface LibraryAPI {

    BooksFront getBooks();

    BookFront getBook(Long bookId);

    Response createBook(BookFront book);

    Collection<AuthorFront>  getAuthors();
}
