package works.buddy.library.dao;

import org.junit.Before;
import org.junit.Test;
import works.buddy.library.model.Book;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class MemoryBookDAOTest {

    // autor > 5 znaków                                                 -- wykonane
    // getBooks zwraca rekordy w kolejności alfabetycznej po autorach   -- wykonane
    // rekordy się nie powtarzają                                       -- wykonane
    // comparator w metodzie sortującej                                 -- wykonane
    // upiększyć test sortowania                                        -- wykonane
    // TODO Zamienić Autora na encję

    private static final int MIN_AUTHOR_NAME = 5;

    private static final int MAX_AUTHOR_NAME = 100;
    private static final String SAMPLE_NAME = "Name";

    private static final String SAMPLE_AUTHOR = "Author";
    private static final String SAMPLE_AUTHOR2 = "Barney Stinson";
    private static final String SAMPLE_AUTHOR3 = "Jordan Peterson";

    private static final Collection<Book> SAMPLE_BOOKS = new HashSet<>(List.of(new Book(1, SAMPLE_NAME, SAMPLE_AUTHOR)));

    public static final String TOO_LONG_AUTHOR = "It is a long established fact that a reader will be distracted by the readable content of a page when";

    private BookDAO bookDAO;

    @Before
    public void setUp() {
        this.bookDAO = new MemoryBookDAO(new HashSet<>());
    }

    @Test
    public void createWithLoad() {
        MemoryBookDAO bookDAO = new MemoryBookDAO(SAMPLE_BOOKS);
        Collection<Book> books = bookDAO.getBooks();
        assertNotNull(books);
        assertEquals(1, books.size());
    }

    @Test
    public void creationThrowingErrorWhenNull() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new MemoryBookDAO(null));
        assertEquals("Parameter books cannot be null", thrown.getMessage());
    }

    @Test
    public void save() {
        bookDAO.save(new Book(1, SAMPLE_NAME, SAMPLE_AUTHOR));
        assertEquals(1, bookDAO.getBooks().size());
        Book book = bookDAO.getBooks().iterator().next();
        assertEquals(SAMPLE_NAME, book.getName());
        assertEquals(SAMPLE_AUTHOR, book.getAuthor());
    }

    @Test
    public void saveThrowingErrorWhenAuthorNameIsNull() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> bookDAO.save(new Book(1, SAMPLE_NAME, null)));
        assertEquals("Author name cannot be null", thrown.getMessage());
    }

    @Test
    public void saveThrowingErrorWhenAuthorTooShort() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> bookDAO.save(new Book(1, SAMPLE_NAME, "")));
        assertEquals(String.format("Author name cannot be shorter than %d characters", MIN_AUTHOR_NAME), thrown.getMessage());
    }

    @Test
    public void saveThrowingErrorWhenAuthorTooLong() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> bookDAO.save(new Book(1, SAMPLE_NAME, TOO_LONG_AUTHOR)));
        assertEquals(String.format("Author name cannot be longer than %d characters", MAX_AUTHOR_NAME), thrown.getMessage());
    }

    @Test
    public void saveTwoSameBooks(){
        bookDAO.save(new Book(1, SAMPLE_NAME, SAMPLE_AUTHOR));
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> bookDAO.save(new Book(1, SAMPLE_NAME, SAMPLE_AUTHOR)));
        assertEquals("Book with this id already exists", thrown.getMessage());
    }

    @Test
    public void getSortedBooks(){
        bookDAO.save(new Book(1, SAMPLE_NAME, SAMPLE_AUTHOR3));
        bookDAO.save(new Book(2, SAMPLE_NAME, SAMPLE_AUTHOR2));
        bookDAO.save(new Book(3, SAMPLE_NAME, SAMPLE_AUTHOR));
        Iterator<Book> iterator = bookDAO.getBooks().iterator();
        assertEquals(SAMPLE_AUTHOR, iterator.next().getAuthor());
        assertEquals(SAMPLE_AUTHOR2, iterator.next().getAuthor());
        assertEquals(SAMPLE_AUTHOR3, iterator.next().getAuthor());
    }
}
