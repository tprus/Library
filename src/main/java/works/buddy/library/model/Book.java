package works.buddy.library.model;

import java.util.Objects;

public class Book{
    private Integer id;

    private String name;

    private Author author;

    public Book(Integer id, String name, Author author) {
        this.name = name;
        this.author = author;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Book book = (Book) o;
        return id.equals(book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String getName() {
        return name;
    }

    public Author getAuthor() {
        return author;
    }
}
