package works.buddy.library;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final List<Book> SAMPLE_BOOKS = new ArrayList<>(
            List.of(new Book("1984", "Scott Chacon and Ben Straub"), new Book("Thinking in Java", "Bruce Eckel")));

    public static void main(String[] args) {
        new ConsoleBookManager(new MemoryBookDAO(SAMPLE_BOOKS), new Scanner(System.in)).run();
    }
}
