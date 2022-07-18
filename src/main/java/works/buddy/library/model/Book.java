package works.buddy.library.model;

import java.util.Random;

public class Book {
    static private int idCounter = 0;
    private int id;
    private String name;

    private String author;

    public Book(String name, String author) {
        this.name = name;
        this.author = author;
        this.id = idCounter++;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }
}
