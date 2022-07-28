package works.buddy.library.api;

import works.buddy.library.api.view.BookFront;

import javax.ws.rs.core.Response;
import java.util.Collection;

public interface LibraryAPI {

    Collection<BookFront> getBooks();

    abstract BookFront getBook(Long bookId);

    Collection<BookFront> getBooksByAuthorId(Long authorId);

    Collection<BookFront> getBooksByAuthor(String authorFirstName, String authorLastName);

    Collection<BookFront> getBooksByTitle(String title);

    Response createBook(BookFront book);
}
