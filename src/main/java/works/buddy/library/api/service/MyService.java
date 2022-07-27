package works.buddy.library.api.service;

import works.buddy.library.api.view.BookFront;

import java.util.Collection;

public interface MyService {

    Collection<BookFront> getBooks();

    Collection<BookFront> getBooksByAuthorId(Integer authorId);
}
