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

    protected void validateAuthorId(Integer id) {
        if (id == null) {
            throw new BadRequestException(2,"id");
        }
        Author author = authorDAO.findOne(id);
        if (author == null) {
            throw new NotFoundException(1, "author",id);
        }
    }

    protected void validateAuthorFront(AuthorFront author) {
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

    protected void validateBookId(Integer id) {
        if (id == null) {
            throw new BadRequestException(2, "id");
        }
        Book book = bookDAO.findOne(id);
        if (book == null) {
            throw new NotFoundException(1, "book", id);
        }
    }

    protected void validateBookFront(BookFront book) {
        if (book.getTitle() == null) {
            throw new BadRequestException(2,"title");
        }
        if (book.getTitle().length() < ApiConstants.MIN_NAME_LENGTH) {
            throw new BadRequestException(3,"title", "longer", ApiConstants.MIN_NAME_LENGTH);
        }
        if (book.getTitle().length() > ApiConstants.MAX_NAME_LENGTH) {
            throw new BadRequestException(3, "title", "shorter", ApiConstants.MAX_NAME_LENGTH);
        }
        AuthorFront author = book.getAuthor();
        if (author == null) {
            throw new BadRequestException(2, "author");
        }
        if (author.getId() == null) {
            throw new BadRequestException(2, "id");
        }
        if (authorDAO.findOne(author.getId()) == null) {
            throw new NotFoundException(1, "author", author.getId());
        }
    }

    protected void validateUpdateAuthor(Integer id, AuthorFront author) {
        validateAuthorId(id);
        validateAuthorFront(author);
    }

    protected void validateUpdateBookFront(Integer bookId, BookFront bookFront) {
        validateBookId(bookId);
        validateBookFront(bookFront);
    }

}
