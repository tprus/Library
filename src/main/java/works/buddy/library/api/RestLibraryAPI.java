package works.buddy.library.api;

import works.buddy.library.api.view.AuthorFront;
import works.buddy.library.api.view.BookFront;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.Collection;

@Path("library")
@Produces("application/xml")
@Consumes( "application/xml" )
public class RestLibraryAPI implements LibraryAPI {

    @Override
    @GET
    @Path("books")
    public Collection<BookFront> getBooks() {
        ArrayList<BookFront> bookFronts = new ArrayList<>();
        BookFront bookFront = new BookFront("Hello World");
        bookFront.setAuthor(new AuthorFront("Juzek"));
        bookFronts.add(bookFront);
        return bookFronts;
    }
}
