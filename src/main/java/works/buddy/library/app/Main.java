package works.buddy.library.app;

import works.buddy.library.dao.MemoryBookDAO;
import works.buddy.library.model.Book;
import works.buddy.library.services.ConsoleBookManager;
import works.buddy.library.ui.ConsoleLibraryFrontend;

import java.util.*;

public class Main {

    private static final Collection<Book> SAMPLE_BOOKS = new HashSet<>(
            List.of(new Book(1 , "Zen", "Scott Chacon and Ben Straub"), new Book(2,"Thinking in Java", "Bruce Eckel"), new Book(3, "Sssss", "Ettttt")));

    public static void main(String[] args) {
        new ConsoleBookManager(new MemoryBookDAO(SAMPLE_BOOKS), new ConsoleLibraryFrontend(new Scanner(System.in))).run();
    }
}
