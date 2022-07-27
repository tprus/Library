package works.buddy.library.api;

import works.buddy.library.api.view.BookFront;

import javax.ws.rs.core.Response;
import java.util.Collection;

public interface LibraryAPI {

    Collection<BookFront> getBooks();

    Collection<BookFront> getBooksByAuthorId(Integer authorId);

    Collection<BookFront> getBooksByAuthor(String authorfirstName, String authorLastName);

    Collection<BookFront> getBooksByTitle(String title);

}
