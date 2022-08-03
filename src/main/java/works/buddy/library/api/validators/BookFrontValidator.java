package works.buddy.library.api.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import works.buddy.library.api.ApiConstants;
import works.buddy.library.api.errors.BadRequestException;
import works.buddy.library.api.errors.NotFoundException;
import works.buddy.library.api.view.AuthorFront;
import works.buddy.library.api.view.BookFront;
import works.buddy.library.dao.AuthorDAO;
import works.buddy.library.dao.BookDAO;
import works.buddy.library.model.Book;

@Service
public class BookFrontValidator {

    @Autowired
    @Qualifier("hibernateBookDAO")
    protected BookDAO bookDAO;

    @Autowired
    protected AuthorDAO authorDAO;

    private final String TITLE = "title";
    private final String AUTHOR = "author";
    private final String ID = "id";
    private final String LONGER = "longer";
    private final String SHORTER = "shorter";

    public void validateBookId(Integer id) {
        if (id == null) {
            throw new BadRequestException(2, "id");
        }
        Book book = bookDAO.findOne(id);
        if (book == null) {
            throw new NotFoundException(1, "book", id);
        }
    }

    public void validateBookFront(BookFront book) {
        if (book.getTitle() == null) {
            throw new BadRequestException(2, TITLE);
        }
        if (book.getTitle().length() < ApiConstants.MIN_NAME_LENGTH) {
            throw new BadRequestException(3, TITLE, LONGER, ApiConstants.MIN_NAME_LENGTH);
        }
        if (book.getTitle().length() > ApiConstants.MAX_NAME_LENGTH) {
            throw new BadRequestException(3, TITLE, SHORTER, ApiConstants.MAX_NAME_LENGTH);
        }
        AuthorFront author = book.getAuthor();
        if (author == null) {
            throw new BadRequestException(2,AUTHOR);
        }
        if (author.getId() == null) {
            throw new BadRequestException(2, ID);
        }
        if (authorDAO.findOne(author.getId()) == null) {
            throw new NotFoundException(1, AUTHOR, author.getId());
        }
    }

    public void validateUpdateBookFront(Integer bookId, BookFront bookFront) {
        validateBookId(bookId);
        validateBookFront(bookFront);
    }
}
