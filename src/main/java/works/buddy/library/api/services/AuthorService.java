package works.buddy.library.api.services;

import works.buddy.library.api.view.AuthorFront;
import works.buddy.library.api.view.AuthorsFront;

public interface AuthorService {

    AuthorsFront getAuthors();

    AuthorFront getAuthor(String authorId);

    AuthorFront createAuthor(AuthorFront author);

    AuthorFront updateAuthor(String id, AuthorFront author);
}
