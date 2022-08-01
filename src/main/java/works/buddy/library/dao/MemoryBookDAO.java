package works.buddy.library.dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import works.buddy.library.model.Author;
import works.buddy.library.model.Book;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Service
@Component("memoryBookDAO")
public class MemoryBookDAO implements BookDAO {

    private static final Collection<Book> SAMPLE_BOOKS = new HashSet<>(
            List.of(new Book(1, "Zen", new Author("Scott", "Chacon")), new Book(2, "Thinking in Java", new Author("Bruce", "Eckel"))));

    private static final int MIN_NAME_LENGTH = 5;

    private static final int MAX_NAME_LENGTH = 100;

    private Collection<Book> books;

    public MemoryBookDAO(Collection<Book> books) {
        if (books == null) {
            throw new IllegalArgumentException("Parameter books cannot be null");
        }
        this.books = books;
    }

    @PostConstruct
    public void init() {
        books = SAMPLE_BOOKS;
    }

    @Override
    public void save(Book book) {
        validate(book);
        this.books.add(book);
    }

    @Override
    public void update(Book entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Book entity) {
        this.books.remove(entity);
    }

    @Override
    public Book findOne(Integer id) {
        throw new UnsupportedOperationException();
    }

    private void validate(Book book) {
        validateNotNull(book.getId(), "Id");
        if (books.contains(book)) {
            throw new IllegalArgumentException("Book with this id already exists");
        }
        validateName(book.getTitle(), "Title");
        Author author = book.getAuthor();
        if (author == null) {
            throw new IllegalArgumentException("Author cannot be null");
        }
        validateName(author.getFirstName(), "Author first name");
        validateName(author.getLastName(), "Author last name");
    }

    private void validateName(String value, String fieldName) {
        validateNotNull(value, fieldName);
        int length = value.length();
        if (length < MIN_NAME_LENGTH) {
            throw new IllegalArgumentException(String.format("%s cannot be shorter than %d characters", fieldName, MIN_NAME_LENGTH));
        }
        if (length > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(String.format("%s cannot be longer than %d characters", fieldName, MAX_NAME_LENGTH));
        }
    }

    private void validateNotNull(Object value, String fieldName) {
        if (value == null) {
            throw new IllegalArgumentException(String.format("%s is required", fieldName));
        }
    }

    @Override
    public List<Book> findAll() {
        return new ArrayList<>(books);
    }

    @Override
    public Collection<Book> findByAuthorId(Integer author) {
        return null;
    }

    @Override
    public Collection<Book> findByTitle(String title) {
        return null;
    }

    @Override
    public Collection<Book> findByAuthor(Author author) {
        return null;
    }

    @Override
    public Book findMostRecent() {
        return null;
    }
}