package works.buddy.library.api.services;

import works.buddy.library.api.view.AuthorFront;
import works.buddy.library.api.view.AuthorsFront;

public interface AuthorService {

    AuthorsFront getAuthors();

    AuthorFront getAuthor(Integer authorId);

    AuthorFront addAuthor(AuthorFront author);

    AuthorFront updateAuthor(Integer id, AuthorFront author);

    void deleteBook(Integer id);
}
