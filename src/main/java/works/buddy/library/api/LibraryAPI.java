package works.buddy.library.api;

import works.buddy.library.api.view.BookFront;

import java.util.Collection;

public interface LibraryAPI {

    Collection<BookFront> getBooks();

    Collection<BookFront> getBooksByAuthorId(Integer authorId);

    Collection<BookFront> getBooksByAuthor(String authorFirstName, String authorLastName);

    Collection<BookFront> getBooksByTitle(String title);
}
