package works.buddy.library.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import works.buddy.library.api.services.AuthorService;
import works.buddy.library.api.services.BookService;
import works.buddy.library.api.view.AuthorFront;
import works.buddy.library.api.view.BookFront;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Collection;

@Path("library")
@Produces("application/xml")
@Consumes("application/xml")
@Service
public class RestLibraryAPI implements LibraryAPI {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @Override
    @GET
    @Path("books")
    public Collection<BookFront> getBooks() {
        return bookService.getBooks();
    }

    @Override
    @GET
    @Path("books/{id}")
    public BookFront getBook(@PathParam("id") Long bookId) {
        return bookService.getBook(bookId);
    }

    @Override
    @POST
    @Path("books")
    public Response createBook(BookFront book) {
        bookService.createBook(book);
        return Response.ok(book).build();
    }

    @Override
    @GET
    @Path("authors")
    public Collection<AuthorFront> getAuthors() {
        return authorService.getAuthors();
    }
}
