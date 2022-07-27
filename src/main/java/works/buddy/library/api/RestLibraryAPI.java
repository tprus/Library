package works.buddy.library.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import works.buddy.library.api.service.MyService;
import works.buddy.library.api.view.BookFront;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.Collection;

@Path("library")
@Produces("application/xml")
@Consumes("application/xml")
@Service
public class RestLibraryAPI implements LibraryAPI {

    @Autowired
    @Qualifier("bookService")
    MyService service;

    //    @Override
//    @GET
//    @Path("books")
//    public Collection<BookFront> getBooks() {
//        ArrayList<BookFront> bookFronts = new ArrayList<>();
//        BookFront bookFront1 = new BookFront("Pro Git");
//        BookFront bookFront2 = new BookFront("Thinking in Java");
//        bookFront1.setAuthor(new AuthorFront("Scott", "Chacon"));
//        bookFront2.setAuthor(new AuthorFront("Bruce", "Eckel"));
//        bookFronts.add(bookFront1);
//        bookFronts.add(bookFront2);
//        return bookFronts;
//    }
    @Override
    @GET
    @Path("books")
    public Collection<BookFront> getBooks() {
        return service.getBooks();
    }

}
