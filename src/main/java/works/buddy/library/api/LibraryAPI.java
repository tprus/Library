package works.buddy.library.api;

import works.buddy.library.api.view.BookFront;

import java.util.Collection;

public interface LibraryAPI {

    Collection<BookFront> getBooks();
}
