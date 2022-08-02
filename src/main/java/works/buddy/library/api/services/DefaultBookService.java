package works.buddy.library.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import works.buddy.library.api.errors.BadRequestException;
import works.buddy.library.api.errors.NotFoundException;
import works.buddy.library.api.view.AuthorFront;
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
        Book book = getBook(bookFront);
        book.setAuthor(authorDAO.findOne(bookFront.getAuthor().getId()));
        bookDAO.save(book);
        return new BookFront(book);
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
            throw new BadRequestException("'author' is required");
        }
        if (author.getId() == null) {
            throw new BadRequestException("'author.id' is required");
        }
        if (authorDAO.findOne(author.getId()) == null) {
            throw new NotFoundException("Author with such id cannot be found");
        }
    }
}
