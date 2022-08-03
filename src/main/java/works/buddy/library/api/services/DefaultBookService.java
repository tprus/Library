package works.buddy.library.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import works.buddy.library.api.validators.BookFrontValidator;
import works.buddy.library.api.view.BookFront;
import works.buddy.library.api.view.BooksFront;
import works.buddy.library.dao.AuthorDAO;
import works.buddy.library.dao.BookDAO;
import works.buddy.library.model.Book;

import java.util.ArrayList;
import java.util.Collection;

@Service
@Transactional
public class DefaultBookService implements BookService {

    @Autowired
    @Qualifier("hibernateBookDAO")
    protected BookDAO bookDAO;

    @Autowired
    protected AuthorDAO authorDAO;

    @Autowired
    private BookFrontValidator validator;

    @Override
    public BooksFront getBooks() {
        return getBookFronts(bookDAO.findAll());
    }

    @Override
    public BookFront getBook(Integer id) {
        validator.validateBookId(id);
        return getBookFront(bookDAO.findOne(id));
    }

    @Override
    public BookFront addBook(BookFront bookFront) {
        validator.validateBookFront(bookFront);
        Book book = getBook(bookFront);
        book.setAuthor(authorDAO.findOne(bookFront.getAuthor().getId()));
        bookDAO.save(book);
        return new BookFront(book);
    }

    @Override
    public BookFront updateBook(Integer bookId, BookFront bookFront) {
        validator.validateUpdateBookFront(bookId, bookFront);
        Book book = bookDAO.findOne(bookId);
        book.setAuthor(authorDAO.findOne(bookFront.getAuthor().getId()));
        book.setTitle(bookFront.getTitle());
        return getBookFront(book);
    }

    @Override
    public void deleteBook(Integer id) {
        validator.validateBookId(id);
        Book returnedObject = bookDAO.findOne(id);
        bookDAO.delete(returnedObject);
    }

    private Book getBook(BookFront book) {
        return new Book(book);
    }

    private BookFront getBookFront(Book book) {
        return new BookFront(book);
    }

    protected BooksFront getBookFronts(Collection<Book> booksToMap) {
        Collection<BookFront> bookFronts = new ArrayList<>();
        for (Book book : booksToMap) {
            bookFronts.add(new BookFront(book));
        }
        return new BooksFront(bookFronts);
    }
}
