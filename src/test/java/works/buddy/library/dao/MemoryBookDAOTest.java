package works.buddy.library.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import works.buddy.library.model.Author;
import works.buddy.library.model.Book;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MemoryBookDAOTest {

    private static final String SAMPLE_NAME = "NameName";

    private static final Author SAMPLE_AUTHOR = new Author("Author", "Authorey");

    private static final Collection<Book> SAMPLE_BOOKS = new HashSet<>(List.of(new Book(1, SAMPLE_NAME, SAMPLE_AUTHOR)));

    public static final String TOO_LONG_NAME = "It is a long established fact that a reader will be distracted by the readable content of a page when";

    public static final String TOO_SHORT_NAME = "Abc";

    private BookDAO bookDAO;

    @BeforeEach
    public void setUp() {
        this.bookDAO = new MemoryBookDAO(new HashSet<>());
    }

    @Test
    public void createWithLoad() {
        MemoryBookDAO bookDAO = new MemoryBookDAO(SAMPLE_BOOKS);
        Collection<Book> books = bookDAO.findAll();
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
        assertEquals(1, bookDAO.findAll().size());
        Book book = bookDAO.findAll().iterator().next();
        assertEquals(SAMPLE_NAME, book.getTitle());
        assertEquals(SAMPLE_AUTHOR, book.getAuthor());
    }

    @Test
    public void saveThrowingErrorWhenIdIsNull() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> bookDAO.save(new Book(1, SAMPLE_NAME, SAMPLE_AUTHOR)));
        assertEquals("Id is required", thrown.getMessage());
    }

    @Test
    public void saveThrowingErrorWhenTitleIsNull() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> bookDAO.save(new Book(1, SAMPLE_NAME, null)));
        assertEquals("Title is required", thrown.getMessage());
    }

    @Test
    public void saveThrowingErrorWhenTitleIsTooShort() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> bookDAO.save(new Book(1, TOO_SHORT_NAME, null)));
        assertEquals("Title cannot be shorter than 5 characters", thrown.getMessage());
    }

    @Test
    public void saveThrowingErrorWhenTitleIsTooLong() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> bookDAO.save(new Book(1, TOO_LONG_NAME, null)));
        assertEquals("Title cannot be longer than 100 characters", thrown.getMessage());
    }

    @Test
    public void saveThrowingErrorWhenAuthorFirstNameIsNull() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> bookDAO.save(new Book(1, SAMPLE_NAME, new Author(null, null))));
        assertEquals("Author first name is required", thrown.getMessage());
    }

    @Test
    public void saveThrowingErrorWhenAuthorLastNameIsNull() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
                () -> bookDAO.save(new Book(1, SAMPLE_NAME, new Author(SAMPLE_NAME, null))));
        assertEquals("Author last name is required", thrown.getMessage());
    }

    @Test
    public void saveThrowingErrorWhenAuthorFirstNameIsTooShort() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
                () -> bookDAO.save(new Book(1, SAMPLE_NAME, new Author(TOO_SHORT_NAME, SAMPLE_NAME))));
        assertEquals("Author first name cannot be shorter than 5 characters", thrown.getMessage());
    }

    @Test
    public void saveThrowingErrorWhenAuthorFirstNameIsTooLong() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
                () -> bookDAO.save(new Book(1, SAMPLE_NAME, new Author(TOO_LONG_NAME, SAMPLE_NAME))));
        assertEquals("Author first name cannot be longer than 100 characters", thrown.getMessage());
    }

    @Test
    public void saveThrowingErrorWhenAuthorLastNameIsTooShort() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
                () -> bookDAO.save(new Book(1, SAMPLE_NAME, new Author(SAMPLE_NAME, TOO_SHORT_NAME))));
        assertEquals("Author last name cannot be shorter than 5 characters", thrown.getMessage());
    }

    @Test
    public void saveThrowingErrorWhenAuthorLastNameIsTooLong() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
                () -> bookDAO.save(new Book(1, SAMPLE_NAME, new Author(SAMPLE_NAME, TOO_LONG_NAME))));
        assertEquals("Author last name cannot be longer than 100 characters", thrown.getMessage());
    }

    @Test
    public void saveTwoSameBooks() {
        bookDAO.save(new Book(1, SAMPLE_NAME, SAMPLE_AUTHOR));
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> bookDAO.save(new Book(1, SAMPLE_NAME, SAMPLE_AUTHOR)));
        assertEquals("Book with this id already exists", thrown.getMessage());
    }
}
