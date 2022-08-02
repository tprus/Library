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
    public BookFront getBook(String id) {
        validateBookId(id);
        return getBookFront(bookDAO.findOne(Integer.valueOf(id)));
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
        validateUpdateBookFront(bookId, bookFront);
        Book book = bookDAO.findOne(Integer.valueOf(bookId));
        book.setAuthor(authorDAO.findOne(bookFront.getAuthor().getId()));
        book.setTitle(bookFront.getTitle());
        return getBookFront(book);
    }

    @Override
    public BookFront deleteBook(String id) {
        validateBookId(id);
        Book returnedObject = bookDAO.findOne(Integer.valueOf(id));
        bookDAO.delete(returnedObject);
        return getBookFront(returnedObject);
    }

}
