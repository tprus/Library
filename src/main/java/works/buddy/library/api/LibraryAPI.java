package works.buddy.library.api;

import works.buddy.library.api.view.BookFront;

import javax.ws.rs.core.Response;
import java.util.Collection;

public interface LibraryAPI {

    Collection<BookFront> getBooks();

    Response createBook(BookFront book);
}
