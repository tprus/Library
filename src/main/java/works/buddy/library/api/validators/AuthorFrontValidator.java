package works.buddy.library.api.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import works.buddy.library.api.ApiConstants;
import works.buddy.library.api.errors.BadRequestException;
import works.buddy.library.api.errors.NotFoundException;
import works.buddy.library.api.view.AuthorFront;
import works.buddy.library.dao.AuthorDAO;
import works.buddy.library.dao.BookDAO;
import works.buddy.library.model.Author;

@Service
public class AuthorFrontValidator {

    @Autowired
    @Qualifier("hibernateBookDAO")
    private BookDAO bookDAO;

    @Autowired
    private AuthorDAO authorDAO;

    public void validateAuthorId(Integer id) {
        if (id == null) {
            throw new BadRequestException(2, "id");
        }
        Author author = authorDAO.findOne(id);
        if (author == null) {
            throw new NotFoundException(1, "author", id);
        }
    }

    public void validateAuthorFront(AuthorFront author) {
        if (author.getFirstName() == null) {
            throw new BadRequestException(2, "firstName");
        }
        if (author.getFirstName().length() < ApiConstants.MIN_NAME_LENGTH) {
            throw new BadRequestException(3, "firstName", "longer", ApiConstants.MIN_NAME_LENGTH);
        }
        if (author.getFirstName().length() > ApiConstants.MAX_NAME_LENGTH) {
            throw new BadRequestException(3, "firstName", "shorter", ApiConstants.MAX_NAME_LENGTH);
        }
        if (author.getLastName() == null) {
            throw new BadRequestException(2, "lastName");
        }
        if (author.getLastName().length() < ApiConstants.MIN_NAME_LENGTH) {
            throw new BadRequestException(3, "lastName", "longer", ApiConstants.MIN_NAME_LENGTH);
        }
        if (author.getLastName().length() > ApiConstants.MAX_NAME_LENGTH) {
            throw new BadRequestException(3, "lastName", "shorter", ApiConstants.MAX_NAME_LENGTH);
        }
    }

    public void validateUpdateAuthor(Integer id, AuthorFront author) {
        validateAuthorId(id);
        validateAuthorFront(author);
    }

}
