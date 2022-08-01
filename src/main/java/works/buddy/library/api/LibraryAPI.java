package works.buddy.library.api;

import works.buddy.library.api.view.AuthorFront;
import works.buddy.library.api.view.AuthorsFront;
import works.buddy.library.api.view.BookFront;
import works.buddy.library.api.view.BooksFront;

import javax.ws.rs.core.Response;

public interface LibraryAPI {

    BooksFront getBooks();

    BookFront getBook(Long bookId);

    Response createBook(BookFront book);

    AuthorsFront getAuthors();

    AuthorFront getAuthor(Long authorId);

    Response createAuthor(AuthorFront author);
}
