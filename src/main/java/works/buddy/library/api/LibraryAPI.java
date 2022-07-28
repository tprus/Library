package works.buddy.library.api;

import works.buddy.library.api.view.AuthorFront;
import works.buddy.library.api.view.BookFront;

import javax.ws.rs.core.Response;
import java.util.Collection;

public interface LibraryAPI {

    Collection<BookFront> getBooks();

    BookFront getBook(Long bookId);

    Response createBook(BookFront book);

    Collection<AuthorFront>  getAuthors();
}
