package works.buddy.library.api.services;

import works.buddy.library.api.view.AuthorFront;
import works.buddy.library.api.view.AuthorsFront;
import works.buddy.library.model.Author;

public interface AuthorService {

    AuthorsFront getAuthors();

    AuthorFront getAuthor(Integer authorId);

    AuthorFront addAuthor(AuthorFront author);

    AuthorFront updateAuthor(AuthorFront author);

    void deleteBook(Integer id);

    Author getAuthorOrException(Integer id);
}
