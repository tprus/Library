package works.buddy.library.api.services;

import works.buddy.library.api.view.AuthorFront;

import java.util.Collection;

public interface AuthorService {

    Collection<AuthorFront> getAuthors();

    AuthorFront getAuthor(Long authorId);
}
