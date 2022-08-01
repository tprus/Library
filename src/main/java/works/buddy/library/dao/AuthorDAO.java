package works.buddy.library.dao;

import works.buddy.library.model.Author;

public interface AuthorDAO extends GenericDAO<Author> {

    Author findByFullName(String firstName, String lastName);
}
