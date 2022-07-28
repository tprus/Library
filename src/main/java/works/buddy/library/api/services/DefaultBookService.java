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
        return getBookFronts(bookDAO.getBooks());
    }

    @Override
    public BookFront getBook(Long id) {
        return getBookFront(bookDAO.getBook(id));
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

    private AuthorFront getAuthorFront(Author authorToMap) {
        return new AuthorFront(authorToMap);
    }

    private Book getBook(BookFront book) {
        return new Book(book);
    }

    private void validate(BookFront book) {
        AuthorFront author = book.getAuthor();
        if (author == null) {
            throw new IllegalArgumentException("Author cannot be null");
        }
        AuthorFront existingAuthor = getAuthorFront(authorDAO.getAuthorByFullName(book.getAuthor().getFirstName(), book.getAuthor().getLastName()));
        if (book.getAuthor().equals(existingAuthor)) {
            book.setAuthor(existingAuthor);
        }
    }

}
