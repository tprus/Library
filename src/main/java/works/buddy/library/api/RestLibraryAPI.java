package works.buddy.library.api;

import org.springframework.stereotype.Component;
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
@Component("restLibraryAPI")
public class RestLibraryAPI implements LibraryAPI {

    @Override
    @GET
    @Path("books")
    public Collection<BookFront> getBooks() {
        ArrayList<BookFront> bookFronts = new ArrayList<>();
        BookFront bookFront1 = new BookFront("Pro Git");
        BookFront bookFront2 = new BookFront("Thinking in Java");
        bookFront1.setAuthor(new AuthorFront("Scott","Chacon"));
        bookFront2.setAuthor(new AuthorFront("Bruce","Eckel"));
        bookFronts.add(bookFront1);
        bookFronts.add(bookFront2);
        return bookFronts;
    }

}
