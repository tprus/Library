package works.buddy.library.dao;

import org.junit.Before;
import org.junit.Test;
import works.buddy.library.model.Author;
import works.buddy.library.model.Book;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class MemoryBookDAOTest {

    // autor > 5 znaków                                                 -- wykonane
    // getBooks zwraca rekordy w kolejności alfabetycznej po autorach   -- wykonane
    // rekordy się nie powtarzają                                       -- wykonane
    // comparator w metodzie sortującej                                 -- wykonane
    // upiększyć test sortowania                                        -- wykonane
    // Zamienić Autora na encję                                         -- wykonane
    // dodać testowanie zbyt długiego imienia                      -- wykonane
    // TODO dodać testowanie długości nazwy książki

    private static final int MIN_AUTHOR_NAME = 5;

    private static final int MAX_AUTHOR_NAME = 100;
    private static final String SAMPLE_NAME = "NameName";

    private static final Author SAMPLE_AUTHOR = new Author("Author", "Authorey");
    private static final Author SAMPLE_AUTHOR2 = new Author("Jordan", "Peterson");
    private static final Author SAMPLE_AUTHOR3 = new Author("Barney", "Stinson");

    private static final Collection<Book> SAMPLE_BOOKS = new HashSet<>(List.of(new Book(1, SAMPLE_NAME, SAMPLE_AUTHOR)));

    public static final String TOO_LONG_STRING_AUTHOR = "It is a long established fact that a reader will be distracted by the readable content of a page when";
    public static final String TOO_SHORT_STRING_AUTHOR =  "Zoń";

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
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> bookDAO.save(new Book(1, SAMPLE_NAME, new Author(null, null))));
        assertEquals("Author has to have first and last name", thrown.getMessage());
        thrown = assertThrows(IllegalArgumentException.class, () -> bookDAO.save(new Book(1, SAMPLE_NAME, new Author(SAMPLE_NAME, null))));
        assertEquals("Author has to have first and last name", thrown.getMessage());
        thrown = assertThrows(IllegalArgumentException.class, () -> bookDAO.save(new Book(1, SAMPLE_NAME, new Author(null, SAMPLE_NAME))));
        assertEquals("Author has to have first and last name", thrown.getMessage());
    }

    @Test
    public void saveThrowingErrorWhenAuthorFirstNameTooShort() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> bookDAO.save(new Book(1, SAMPLE_NAME, new Author(TOO_SHORT_STRING_AUTHOR, SAMPLE_NAME))));
        assertEquals(String.format("Author name cannot be shorter than %d characters", MIN_AUTHOR_NAME), thrown.getMessage());
    }

    @Test
    public void saveThrowingErrorWhenAuthorFirstNameTooLong() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> bookDAO.save(new Book(1, SAMPLE_NAME, new Author(TOO_LONG_STRING_AUTHOR, SAMPLE_NAME))));
        assertEquals(String.format("Author name cannot be longer than %d characters", MAX_AUTHOR_NAME), thrown.getMessage());
    }
    @Test
    public void saveThrowingErrorWhenAuthorLastNameTooShort() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> bookDAO.save(new Book(1, SAMPLE_NAME, new Author(SAMPLE_NAME, TOO_SHORT_STRING_AUTHOR))));
        assertEquals(String.format("Author name cannot be shorter than %d characters", MIN_AUTHOR_NAME), thrown.getMessage());
    }

    @Test
    public void saveThrowingErrorWhenAuthorLastNameTooLong() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> bookDAO.save(new Book(1, SAMPLE_NAME, new Author(SAMPLE_NAME, TOO_LONG_STRING_AUTHOR))));
        assertEquals(String.format("Author name cannot be longer than %d characters", MAX_AUTHOR_NAME), thrown.getMessage());
    }
    @Test
    public void saveThrowingErrorWhenBookNameTooShort() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> bookDAO.save(new Book(1, TOO_SHORT_STRING_AUTHOR, SAMPLE_AUTHOR)));
        assertEquals(String.format("Book name cannot be shorter than %d characters", MIN_AUTHOR_NAME), thrown.getMessage());
    }

    @Test
    public void saveThrowingErrorWhenBookNameTooLong() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> bookDAO.save(new Book(1, TOO_LONG_STRING_AUTHOR, SAMPLE_AUTHOR)));
        assertEquals(String.format("Book name cannot be longer than %d characters", MAX_AUTHOR_NAME), thrown.getMessage());
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
