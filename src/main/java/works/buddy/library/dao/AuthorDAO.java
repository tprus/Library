package works.buddy.library.dao;

import works.buddy.library.model.Author;

import javax.ws.rs.GET;

public interface AuthorDAO {
    public Author getAuthorByFullName(String firstName, String lastName);
}
