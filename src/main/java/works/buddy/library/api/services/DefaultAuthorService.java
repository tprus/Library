package works.buddy.library.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import works.buddy.library.api.validators.AuthorFrontValidator;
import works.buddy.library.api.view.AuthorFront;
import works.buddy.library.api.view.AuthorsFront;
import works.buddy.library.dao.AuthorDAO;
import works.buddy.library.model.Author;

import java.util.ArrayList;
import java.util.Collection;

@Service
@Transactional
public class DefaultAuthorService implements AuthorService {

    @Autowired
    protected AuthorDAO authorDAO;

    @Autowired
    private AuthorFrontValidator validator;

    @Override
    public AuthorsFront getAuthors() {
        return getAuthorFronts(authorDAO.findAll());
    }

    @Override
    public AuthorFront getAuthor(Integer authorId) {
        validator.validateAuthorId(authorId);
        return getAuthorFront(authorDAO.findOne(authorId));
    }

    @Override
    public AuthorFront addAuthor(AuthorFront authorFront) {
        validator.validateAuthorFront(authorFront);
        Author author = getAuthor(authorFront);
        authorDAO.save(author);
        return getAuthorFront(author);
    }

    @Override
    public AuthorFront updateAuthor(Integer id, AuthorFront authorFront) {
        validator.validateUpdateAuthor(id, authorFront);
        Author author = authorDAO.findOne(id);
        author.setFirstName(authorFront.getFirstName());
        author.setLastName(authorFront.getLastName());
        return getAuthorFront(author);
    }

    @Override
    public void deleteBook(Integer id) {
        validator.validateAuthorId(id);
        Author returnedObject = authorDAO.findOne(Integer.valueOf(id));
        authorDAO.delete(returnedObject);
    }

    protected AuthorsFront getAuthorFronts(Collection<Author> authorsToMap) {
        Collection<AuthorFront> mappedAuthors = new ArrayList<>();
        for (Author author : authorsToMap) {
            mappedAuthors.add(new AuthorFront(author));
        }
        return new AuthorsFront(mappedAuthors);
    }

    protected Author getAuthor(AuthorFront author) {
        return new Author(author);
    }

    protected AuthorFront getAuthorFront(Author author) {
        return new AuthorFront(author);
    }

}
