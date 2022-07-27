package works.buddy.library.api;

import works.buddy.library.api.view.BookFront;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.Collection;

public interface LibraryAPI {

    Collection<BookFront> getBooks();

    Collection<BookFront> getBooksByAuthorId(Integer authorId);
}
