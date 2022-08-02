package works.buddy.library.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import works.buddy.library.api.ApiConstants;
import works.buddy.library.api.errors.BadRequestException;
import works.buddy.library.api.errors.NotFoundException;
import works.buddy.library.api.view.AuthorFront;
import works.buddy.library.api.view.BookFront;
import works.buddy.library.api.view.BooksFront;
import works.buddy.library.dao.AuthorDAO;
import works.buddy.library.dao.BookDAO;
import works.buddy.library.model.Author;
import works.buddy.library.model.Book;

import java.util.ArrayList;
import java.util.Collection;

@Service
@Transactional
public class DefaultBookService implements BookService {

    @Autowired
    @Qualifier("hibernateBookDAO")
    private BookDAO bookDAO;

    @Autowired
    private AuthorDAO authorDAO;

    @Override
    public BooksFront getBooks() {
        return getBookFronts(bookDAO.findAll());
    }

    @Override
    public BookFront getBook(String id) {
        validateId(id);
        return getBookFront(bookDAO.findOne(Integer.valueOf(id)));
    }

    private BookFront getBookFront(Book book) {
        return new BookFront(book);
    }

    @Override
    public BookFront createBook(BookFront bookFront) {
        validateBookFront(bookFront);
        Book book = getBook(bookFront);
        book.setAuthor(authorDAO.findOne(bookFront.getAuthor().getId()));
        bookDAO.save(book);
        return new BookFront(book);
    }

    @Override
    public BookFront updateBook(String bookId, BookFront bookFront) {
        validateUpdate(bookId, bookFront);
        Book book = bookDAO.findOne(Integer.valueOf(bookId));
        book.setAuthor(authorDAO.findOne(bookFront.getAuthor().getId()));
        book.setTitle(bookFront.getTitle());
        return getBookFront(book);
    }

    private BooksFront getBookFronts(Collection<Book> booksToMap) {
        Collection<BookFront> bookFronts = new ArrayList<>();
        for (Book book : booksToMap) {
            bookFronts.add(new BookFront(book));
        }
        return new BooksFront(bookFronts);
    }

    private Book getBook(BookFront book) {
        return new Book(book);
    }

    private Author getAuthor (AuthorFront authorFront) {return new Author(authorFront);}

    private void validateId(String id) {
        if (id == null){
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

    private void validateUpdate(String bookId, BookFront bookFront){
        validateId(bookId);
        validateBookFront(bookFront);
    }

    private void validateBookFront(BookFront book) {
        AuthorFront author = book.getAuthor();
        if (book.getTitle() == null) {
            throw new BadRequestException("'title' is required");
        }
        if (book.getTitle().length() < ApiConstants.MIN_NAME_LENGTH) {
            throw new BadRequestException("'title' has to be longer than " + ApiConstants.MIN_NAME_LENGTH + " characters");
        }
        if (book.getTitle().length() > ApiConstants.MAX_NAME_LENGTH) {
            throw new BadRequestException("'title' has to be shorter than " + ApiConstants.MAX_NAME_LENGTH + " characters");
        }
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

    private Boolean containsOnlyNumbers(String s){
        return s != null && s.matches("[0-9.]+");
    }
}
