package works.buddy.library.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import works.buddy.library.api.services.AuthorService;
import works.buddy.library.api.services.BookService;
import works.buddy.library.api.view.AuthorFront;
import works.buddy.library.api.view.AuthorsFront;
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

    @Autowired
    private AuthorService authorService;

    @Override
    @GET
    @Path("books")
    public BooksFront getBooks() {
        return bookService.getBooks();
    }

    @Override
    @GET
    @Path("books/{id}")
    public BookFront getBook(@PathParam("id") Integer bookId) {
        return bookService.getBook(bookId);
    }

    @Override
    @POST
    @Path("books")
    public Response addBook(BookFront book) {
        return Response.ok(bookService.addBook(book)).build();
    }

    @Override
    @PUT
    @Path("books/{id}")
    public Response updateBook(@PathParam("id") Integer id, BookFront book) {
        book.setId(id);
        return Response.ok(bookService.updateBook(book)).build();
    }

    @Override
    @DELETE
    @Path("books/{id}")
    public Response deleteBook(@PathParam("id") Integer id) {
        bookService.deleteBook(id);
        return Response.ok().build();
    }

    @Override
    @GET
    @Path("authors")
    public AuthorsFront getAuthors() {
        return authorService.getAuthors();
    }

    @Override
    @GET
    @Path("authors/{id}")
    public AuthorFront getAuthor(@PathParam("id") Integer authorId) {
        return authorService.getAuthor(authorId);
    }

    @Override
    @POST
    @Path("authors")
    public Response addAuthor(AuthorFront author) {
        AuthorFront savedAuthor = authorService.addAuthor(author);
        return Response.ok(savedAuthor).build();
    }

    @Override
    @PUT
    @Path("authors/{id}")
    public Response updateAuthor(@PathParam("id") Integer id, AuthorFront author) {
        author.setId(id);
        return Response.ok(authorService.updateAuthor(author)).build();
    }

    @Override
    @DELETE
    @Path("authors/{id}")
    public Response deleteAuthor(@PathParam("id") Integer id) {
        authorService.deleteBook(id);
        return Response.ok().build();
    }
}
