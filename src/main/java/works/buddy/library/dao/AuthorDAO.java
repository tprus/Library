package works.buddy.library.dao;

import works.buddy.library.api.view.AuthorFront;
import works.buddy.library.model.Author;

import java.util.Collection;

public interface AuthorDAO {

    Author getAuthorByFullName(String firstName, String lastName);

    Collection<Author> getAuthors();

    Author GetAuthor(Long authorId);
}
