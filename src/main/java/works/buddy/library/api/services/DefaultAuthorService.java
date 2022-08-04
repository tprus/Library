package works.buddy.library.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import works.buddy.library.api.errors.NotFoundException;
import works.buddy.library.api.view.AuthorFront;
import works.buddy.library.api.view.AuthorsFront;
import works.buddy.library.dao.AuthorDAO;
import works.buddy.library.model.Author;

import java.util.ArrayList;
import java.util.Collection;

import static works.buddy.library.api.validators.AuthorFrontValidator.validateAuthorFront;
import static works.buddy.library.api.validators.AuthorFrontValidator.validateAuthorId;

@Service
@Transactional
public class DefaultAuthorService implements AuthorService {

    @Autowired
    private AuthorDAO authorDAO;

    @Override
    public AuthorsFront getAuthors() {
        return getAuthorFronts(authorDAO.findAll());
    }

    @Override
    public AuthorFront getAuthor(Integer authorId) {
        validateAuthorId(authorId);
        return new AuthorFront(authorDAO.findOne(authorId));
    }

    @Override
    public AuthorFront addAuthor(AuthorFront authorFront) {
        validateAuthorFront(authorFront);
        Author author = new Author(authorFront);
        authorDAO.save(author);
        return new AuthorFront(author);
    }

    @Override
    public AuthorFront updateAuthor(AuthorFront authorFront) {
        Integer id = authorFront.getId();
        validateAuthorId(id);
        validateAuthorFront(authorFront);
        Author author = getAuthorOrException(id);
        author.setFirstName(authorFront.getFirstName());
        author.setLastName(authorFront.getLastName());
        return new AuthorFront(author);
    }

    @Override
    public void deleteBook(Integer id) {
        validateAuthorId(id);
        authorDAO.delete(authorDAO.findOne(id));
    }

    @Override
    public Author getAuthorOrException(Integer id) {
        Author author = authorDAO.findOne(id);
        if (author == null) {
            throw new NotFoundException(101, id);
        }
        return author;
    }

    private AuthorsFront getAuthorFronts(Collection<Author> authorsToMap) {
        Collection<AuthorFront> mappedAuthors = new ArrayList<>();
        for (Author author : authorsToMap) {
            mappedAuthors.add(new AuthorFront(author));
        }
        return new AuthorsFront(mappedAuthors);
    }
}
