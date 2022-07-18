package works.buddy.library.app;

import works.buddy.library.dao.MemoryBookDAO;
import works.buddy.library.model.Book;
import works.buddy.library.services.ConsoleBookManager;
import works.buddy.library.ui.ConsoleLibraryFrontend;

import java.util.*;

public class Main {

    private static final Collection<Book> SAMPLE_BOOKS = new HashSet<>(
            List.of(new Book("1984", "Scott Chacon and Ben Straub"), new Book("Thinking in Java", "Bruce Eckel")));

    public static void main(String[] args) {
        new ConsoleBookManager(new MemoryBookDAO(SAMPLE_BOOKS), new ConsoleLibraryFrontend(new Scanner(System.in))).run();
    }
}
