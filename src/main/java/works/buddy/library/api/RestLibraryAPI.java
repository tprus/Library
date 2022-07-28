package works.buddy.library.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import works.buddy.library.api.services.BookService;
import works.buddy.library.api.view.BookFront;
import works.buddy.library.api.view.BooksFront;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("library")
@Produces("application/json")
@Consumes("application/json")
@Service
public class RestLibraryAPI implements LibraryAPI {

    @Autowired
    private BookService bookService;

    @Override
    @GET
    @Path("books")
    public BooksFront getBooks() {
        return bookService.getBooks();
    }

    @Override
    @POST
    @Path("books")
    public Response createBook(BookFront book) {
        bookService.createBook(book);
        return Response.ok(book).build();
    }
}
