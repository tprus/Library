package works.buddy.library.api.services;

import org.apache.cxf.interceptor.Fault;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
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
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

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
    public void createBook(BookFront book) {
        validate(book);
        bookDAO.save(getBook(book));
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
        if(authorDAO.findOne(author.getId()) == null){
            throw new NotFoundException("Provide id of existing author");
        }

    }

}
