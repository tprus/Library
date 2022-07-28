package works.buddy.library.dao;

import works.buddy.library.model.Author;

public interface AuthorDAO {

    Author getAuthorByFullName(String firstName, String lastName);
}
