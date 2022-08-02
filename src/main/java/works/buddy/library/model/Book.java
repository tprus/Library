package works.buddy.library.model;

import org.hibernate.annotations.Cascade;
import works.buddy.library.api.view.BookFront;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "book")
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.MERGE)
    @JoinColumn(name = "author_id")
    private Author author;

    public Book() {
    }

    public Book(String title, Author author) {
        this.title = title;
        this.author = author;
    }

    public Book(Integer id, String title, Author author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public Book(BookFront book) {
        this.title = book.getTitle();
        this.author = new Author(book.getAuthor().getId(), book.getAuthor().getFirstName(), book.getAuthor().getLastName());
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

    @Override
    public String toString() {
        return "ID: " + getId() + ", Name: " + getTitle() + ", Author first name: " + getAuthor().getFirstName() + ", Author Last Name: " +
                getAuthor().getLastName();
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
