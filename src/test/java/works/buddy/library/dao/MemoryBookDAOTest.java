package works.buddy.library.dao;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import works.buddy.library.model.Book;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MemoryBookDAOTest {
    // autor > 5 znaków
    // getBooks zwraca rekordy w kolejności alfabetycznej po autorach
    //rekordy się nie powtarzają
    
    private static final String SAMPLE_NAME = "Name";

    private static final String SAMPLE_AUTHOR = "Author";

    private static final List<Book> SAMPLE_BOOKS = new ArrayList<>(List.of(new Book(SAMPLE_NAME, SAMPLE_AUTHOR)));

    private BookDAO bookDAO;

    @BeforeEach
    public void setUp() {
        this.bookDAO = new MemoryBookDAO(new ArrayList<>());
    }

    @Test
    public void createWithLoad() {
        this.bookDAO = new MemoryBookDAO(SAMPLE_BOOKS);
        assertEquals(1, bookDAO.getBooks().size());
    }

    @Test
    public void creationThrowingErrorWhenNull() {
        assertThrows(IllegalArgumentException.class, () -> new MemoryBookDAO(null), "Parameter book cannot be null");
    }

    @Test
    public void getSomeBooks() {
        MemoryBookDAO bookDAO = new MemoryBookDAO(SAMPLE_BOOKS);
        Collection<Book> books = bookDAO.getBooks();
        assertNotNull(books);
        assertEquals(1, books.size());
    }

    @Test
    public void save() {
        MemoryBookDAO bookDAO = new MemoryBookDAO(new ArrayList<>());
        bookDAO.save(new Book(SAMPLE_NAME, SAMPLE_AUTHOR));
        assertEquals(1, bookDAO.getBooks().size());
        Book book = bookDAO.getBooks().iterator().next();
        assertEquals(SAMPLE_NAME, book.getName());
        assertEquals(SAMPLE_AUTHOR, book.getAuthor());
    }
}
