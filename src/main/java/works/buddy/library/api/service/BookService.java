package works.buddy.library.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import works.buddy.library.api.view.BookFront;
import works.buddy.library.dao.BookDAO;
import works.buddy.library.model.Author;
import works.buddy.library.model.Book;

import java.util.ArrayList;
import java.util.Collection;

@Service
@Transactional
@Component("bookService")
public class BookService implements MyService {

    @Autowired
    @Qualifier("hibernateBookDAO")
    BookDAO bookDAO;

    @Override
    public Collection<BookFront> getBooks() {
        return mapBookCollection(bookDAO.getBooks());
    }

    @Override
    public Collection<BookFront> getBooksByAuthorId(Integer authorId) {
        return mapBookCollection(bookDAO.getBooksByAuthorId(authorId));
    }

    @Override
    public Collection<BookFront> getBooksByAuthor(String authorFirstName, String authorLastName) {
        return mapBookCollection(bookDAO.getBooksByAuthor(new Author(authorFirstName, authorLastName)));
    }

    @Override
    public Collection<BookFront> getBooksByTitle(String title) {
        return mapBookCollection(bookDAO.getBooksByTitle(title));
    }

    private Collection<BookFront> mapBookCollection(Collection<Book> booksToMap) {
        Collection<BookFront> mappedBooks = new ArrayList<>();
        for (Book book : booksToMap) {
            mappedBooks.add(new BookFront(book));
        }
        return mappedBooks;
    }
}
