package works.buddy.library.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import works.buddy.library.api.errors.NotFoundException;
import works.buddy.library.api.view.BookFront;
import works.buddy.library.api.view.BooksFront;
import works.buddy.library.dao.BookDAO;
import works.buddy.library.model.Book;

import java.util.ArrayList;
import java.util.Collection;

import static works.buddy.library.api.validators.BookFrontValidator.validateBookFront;
import static works.buddy.library.api.validators.BookFrontValidator.validateBookId;

@Service
@Transactional
public class DefaultBookService implements BookService {

    @Autowired
    @Qualifier("hibernateBookDAO")
    private BookDAO bookDAO;

    @Autowired
    private AuthorService authorService;

    @Override
    public BooksFront getBooks() {
        return getBookFronts(bookDAO.findAll());
    }

    @Override
    public BookFront getBook(Integer id) {
        validateBookId(id);
        return new BookFront(getBookOrException(id));
    }

    @Override
    public BookFront addBook(BookFront bookFront) {
        validateBookFront(bookFront);
        Book book = new Book(bookFront);
        book.setAuthor(authorService.getAuthorOrException(bookFront.getAuthor().getId()));
        bookDAO.save(book);
        return new BookFront(book);
    }

    @Override
    public BookFront updateBook(BookFront bookFront) {
        Integer bookId = bookFront.getId();
        validateBookId(bookId);
        validateBookFront(bookFront);
        Book book = getBookOrException(bookId);
        book.setAuthor(authorService.getAuthorOrException(bookFront.getAuthor().getId()));
        book.setTitle(bookFront.getTitle());
        return new BookFront(book);
    }

    @Override
    public void deleteBook(Integer id) {
        validateBookId(id);
        bookDAO.delete(getBookOrException(id));
    }

    private Book getBookOrException(Integer id) {
        Book book = bookDAO.findOne(id);
        if (book == null) {
            throw new NotFoundException(100, id);
        }
        return book;
    }

    private BooksFront getBookFronts(Collection<Book> booksToMap) {
        Collection<BookFront> bookFronts = new ArrayList<>();
        for (Book book : booksToMap) {
            bookFronts.add(new BookFront(book));
        }
        return new BooksFront(bookFronts);
    }
}
