package works.buddy.library.api;

import works.buddy.library.api.view.AuthorFront;
import works.buddy.library.api.view.BookFront;
import works.buddy.library.api.view.BooksFront;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.Collection;

public interface LibraryAPI {

    BooksFront getBooks();

    BookFront getBook(Long bookId);

    Response createBook(BookFront book);

    Collection<AuthorFront>  getAuthors();


    AuthorFront getAuthor(Long authorId);

    Response createAuthor(AuthorFront author);
}
