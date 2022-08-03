package works.buddy.library.api;

import works.buddy.library.api.view.AuthorFront;
import works.buddy.library.api.view.AuthorsFront;
import works.buddy.library.api.view.BookFront;
import works.buddy.library.api.view.BooksFront;

import javax.ws.rs.core.Response;

public interface LibraryAPI {

    BooksFront getBooks();

    BookFront getBook(Integer bookId);

    Response updateBook(Integer bookId, BookFront book);

    Response addBook(BookFront book);

    Response deleteBook(Integer id);

    AuthorsFront getAuthors();

    AuthorFront getAuthor(Integer authorId);

    Response addAuthor(AuthorFront author);

    Response updateAuthor(Integer id, AuthorFront author);

    Response deleteAuthor(Integer id);
}
