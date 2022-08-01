package works.buddy.library.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import works.buddy.library.api.view.AuthorFront;
import works.buddy.library.api.view.BookFront;
import works.buddy.library.api.view.BooksFront;
import works.buddy.library.dao.AuthorDAO;
import works.buddy.library.dao.BookDAO;
import works.buddy.library.model.Book;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

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
    public BookFront getBook(Integer id) {
        return getBookFront(bookDAO.findOne(id));
    }

    private BookFront getBookFront(Book book) {
        return new BookFront(book);
    }

    @Override
    public BookFront createBook(BookFront bookFront) {
        validate(bookFront);
        BookFront returnedBook = getBookFront(getLastElement(bookDAO.findByTitle(bookFront.getTitle())));
        bookDAO.save(getBook(bookFront));
        return returnedBook;
    }

    public Book getLastElement(final Collection<Book> c) {
        final Iterator itr = c.iterator();
        Book lastElement = (Book) itr.next();
        while (itr.hasNext()) {
            lastElement = (Book) itr.next();
        }
        return lastElement;
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

    private void validate(BookFront book) {
        AuthorFront author = book.getAuthor();
        if (author == null) {
            throw new BadRequestException("Author id is required");
        }
        if (authorDAO.findOne(author.getId()) == null) {
            throw new NotFoundException("Provide id of existing author");
        }

    }

}
