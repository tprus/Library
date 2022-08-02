package works.buddy.library.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import works.buddy.library.api.ApiConstants;
import works.buddy.library.api.errors.BadRequestException;
import works.buddy.library.api.errors.NotFoundException;
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
    private AuthorDAO authorDAO;

    @Override
    public AuthorsFront getAuthors() {
        return getAuthorFronts(authorDAO.findAll());
    }

    @Override
    public AuthorFront getAuthor(String authorId) {
        validateId(authorId);
        return getAuthorFront(authorDAO.findOne(Integer.valueOf(authorId)));
    }

    @Override
    public AuthorFront createAuthor(AuthorFront authorFront) {
        validateAuthorFront(authorFront);
        Author author = getAuthor(authorFront);
        authorDAO.save(author);
        return getAuthorFront(author);
    }

    @Override
    public AuthorFront updateAuthor(String id, AuthorFront authorFront) {
        validateUpdate(id, authorFront);
        Author author = authorDAO.findOne(Integer.valueOf(id));
        author.setFirstName(authorFront.getFirstName());
        author.setLastName(authorFront.getLastName());
        return getAuthorFront(author);
    }

    @Override
    public AuthorFront deleteBook(String id) {
        validateId(id);
        Author returnedObject = authorDAO.findOne(Integer.valueOf(id));
        authorDAO.delete(returnedObject);
        return getAuthorFront(returnedObject);
    }

    private Author getAuthor(AuthorFront author) {
        return new Author(author);
    }

    private AuthorFront getAuthorFront(Author author) {
        return new AuthorFront(author);
    }

    private AuthorsFront getAuthorFronts(Collection<Author> authorsToMap) {
        Collection<AuthorFront> mappedAuthors = new ArrayList<>();
        for (Author author : authorsToMap) {
            mappedAuthors.add(new AuthorFront(author));
        }
        return new AuthorsFront(mappedAuthors);
    }

    private void validateId(String id) {
        if (id == null) {
            throw new BadRequestException("'id' is required");
        }
        if (!containsOnlyNumbers(id)) {
            throw new BadRequestException("'id' has to be in Integer format");
        }
        Integer idFromString = Integer.valueOf(id);
        Author author = authorDAO.findOne(idFromString);
        if (author == null) {
            throw new NotFoundException("There is no author with that ip");
        }
    }

    private void validateUpdate(String id, AuthorFront author) {
        validateId(id);
        validateAuthorFront(author);
    }

    private void validateAuthorFront(AuthorFront author) {
        if (author.getFirstName() == null) {
            throw new BadRequestException("Authors 'firstName' is required");
        }
        if (author.getFirstName().length() < ApiConstants.MIN_NAME_LENGTH) {
            throw new BadRequestException("Authors 'firstName' has to be longer than " + ApiConstants.MIN_NAME_LENGTH + " characters");
        }
        if (author.getFirstName().length() > ApiConstants.MAX_NAME_LENGTH) {
            throw new BadRequestException("Authors 'firstName' has to be shorter than " + ApiConstants.MAX_NAME_LENGTH + " characters");
        }
        if (author.getLastName() == null) {
            throw new BadRequestException("Authors 'lastName' is required");
        }
        if (author.getLastName().length() < ApiConstants.MIN_NAME_LENGTH) {
            throw new BadRequestException("Authors 'lastName' has to be longer than " + ApiConstants.MIN_NAME_LENGTH + " characters");
        }
        if (author.getLastName().length() > ApiConstants.MAX_NAME_LENGTH) {
            throw new BadRequestException("Authors 'lastName' has to be shorter than " + ApiConstants.MAX_NAME_LENGTH + " characters");
        }
    }

    private Boolean containsOnlyNumbers(String s) {
        return s.matches("[0-9.]+");
    }
}
