package works.buddy.library.api.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import works.buddy.library.api.view.BookFront;
import works.buddy.library.api.view.BooksFront;
import works.buddy.library.model.Book;

@Service
@Transactional
public class DefaultBookService extends AbstractDefaultService implements BookService {

    @Override
    public BooksFront getBooks() {
        return getBookFronts(bookDAO.findAll());
    }

    @Override
    public BookFront getBook(Integer id) {
        validateBookId(id);
        return getBookFront(bookDAO.findOne(id));
    }

    @Override
    public BookFront addBook(BookFront bookFront) {
        validateBookFront(bookFront);
        Book book = getBook(bookFront);
        book.setAuthor(authorDAO.findOne(bookFront.getAuthor().getId()));
        bookDAO.save(book);
        return new BookFront(book);
    }

    @Override
    public BookFront updateBook(Integer bookId, BookFront bookFront) {
        validateUpdateBookFront(bookId, bookFront);
        Book book = bookDAO.findOne(bookId);
        book.setAuthor(authorDAO.findOne(bookFront.getAuthor().getId()));
        book.setTitle(bookFront.getTitle());
        return getBookFront(book);
    }

    @Override
    public void deleteBook(Integer id) {
        validateBookId(id);
        Book returnedObject = bookDAO.findOne(id);
        bookDAO.delete(returnedObject);
    }

}
