package works.buddy.library.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import works.buddy.library.api.services.BookService;
import works.buddy.library.api.view.BookFront;

import javax.ws.rs.*;
import java.util.Collection;

@Path("library")
@Produces("application/xml")
@Consumes("application/xml")
@Service
public class RestLibraryAPI implements LibraryAPI {

    @Autowired
    @Qualifier("bookService")
    BookService bookService;

    @Override
    @GET
    @Path("books")
    public Collection<BookFront> getBooks() {
        return bookService.getBooks();
    }

    @Override
    @GET
    @Path("books_by_author_id/{authorId}")
    public Collection<BookFront> getBooksByAuthorId(@PathParam("authorId") Integer authorId) {
        return bookService.getBooksByAuthorId(authorId);
    }

    @Override
    @GET
    @Path("books_by_author/{authorFirstName}_{authorLastName}")
    public Collection<BookFront> getBooksByAuthor(@PathParam("authorFirstName") String authorFirstName, @PathParam("authorLastName") String authorLastName) {
        return bookService.getBooksByAuthor(authorFirstName, authorLastName);
    }

    @Override
    @GET
    @Path("books_by_title/{bookTitle}")
    public Collection<BookFront> getBooksByTitle(@PathParam("bookTitle") String title) {
        return bookService.getBooksByTitle(title);
    }
}
