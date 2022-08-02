package works.buddy.library.api;

import works.buddy.library.api.view.AuthorFront;
import works.buddy.library.api.view.AuthorsFront;
import works.buddy.library.api.view.BookFront;
import works.buddy.library.api.view.BooksFront;

import javax.ws.rs.core.Response;

public interface LibraryAPI {

    BooksFront getBooks();

    BookFront getBook(String bookId);

    Response updateBook(String bookId, BookFront book);

    Response createBook(BookFront book);

    Response deleteBook(String id);

    AuthorsFront getAuthors();

    AuthorFront getAuthor(String authorId);

    Response createAuthor(AuthorFront author);

    Response updateAuthor(String id, AuthorFront author);

    Response deleteAuthor(String id);
}
