package works.buddy.library.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import works.buddy.library.api.ApiConstants;
import works.buddy.library.api.errors.BadRequestException;
import works.buddy.library.api.errors.NotFoundException;
import works.buddy.library.api.view.AuthorFront;
import works.buddy.library.api.view.AuthorsFront;
import works.buddy.library.api.view.BookFront;
import works.buddy.library.api.view.BooksFront;
import works.buddy.library.dao.AuthorDAO;
import works.buddy.library.dao.BookDAO;
import works.buddy.library.model.Author;
import works.buddy.library.model.Book;

import java.util.ArrayList;
import java.util.Collection;

public abstract class AbstractDefaultService {

    @Autowired
    @Qualifier("hibernateBookDAO")
    protected BookDAO bookDAO;

    @Autowired
    protected AuthorDAO authorDAO;

    protected Book getBook(BookFront book) {
        return new Book(book);
    }

    protected BookFront getBookFront(Book book) {
        return new BookFront(book);
    }

    protected Author getAuthor(AuthorFront author) {
        return new Author(author);
    }

    protected AuthorFront getAuthorFront(Author author) {
        return new AuthorFront(author);
    }

    protected AuthorsFront getAuthorFronts(Collection<Author> authorsToMap) {
        Collection<AuthorFront> mappedAuthors = new ArrayList<>();
        for (Author author : authorsToMap) {
            mappedAuthors.add(new AuthorFront(author));
        }
        return new AuthorsFront(mappedAuthors);
    }

    protected BooksFront getBookFronts(Collection<Book> booksToMap) {
        Collection<BookFront> bookFronts = new ArrayList<>();
        for (Book book : booksToMap) {
            bookFronts.add(new BookFront(book));
        }
        return new BooksFront(bookFronts);
    }

    private Boolean containsOnlyNumbers(String s) {
        return s.matches("[0-9.]+");
    }

    protected void validateAuthorId(String id) {
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

    protected void validateAuthorFront(AuthorFront author) {
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

    protected void validateBookId(String id) {
        if (id == null) {
            throw new BadRequestException("'id' is required");
        }
        if (!containsOnlyNumbers(id)) {
            throw new BadRequestException("'id' has to be in Integer format");
        }
        Integer idFromString = Integer.valueOf(id);
        Book book = bookDAO.findOne(idFromString);
        if (book == null) {
            throw new NotFoundException("There is no book with that ip");
        }
    }

    protected void validateBookFront(BookFront book) {
        if (book.getTitle() == null) {
            throw new BadRequestException("'title' is required");
        }
        if (book.getTitle().length() < ApiConstants.MIN_NAME_LENGTH) {
            throw new BadRequestException("'title' has to be longer than " + ApiConstants.MIN_NAME_LENGTH + " characters");
        }
        if (book.getTitle().length() > ApiConstants.MAX_NAME_LENGTH) {
            throw new BadRequestException("'title' has to be shorter than " + ApiConstants.MAX_NAME_LENGTH + " characters");
        }
        AuthorFront author = book.getAuthor();
        if (author == null) {
            throw new BadRequestException("'author' is required");
        }
        if (author.getId() == null) {
            throw new BadRequestException("'author.id' is required");
        }
        if (authorDAO.findOne(author.getId()) == null) {
            throw new NotFoundException("Author with such id cannot be found");
        }
    }

    protected void validateUpdateAuthor(String id, AuthorFront author) {
        validateAuthorId(id);
        validateAuthorFront(author);
    }

    protected void validateUpdateBookFront(String bookId, BookFront bookFront) {
        validateBookId(bookId);
        validateBookFront(bookFront);
    }

}
