package works.buddy.library.api.view;

import works.buddy.library.model.Book;

import java.io.Serializable;

public class BookFront implements Serializable {

    private Integer id;

    private String title;

    private AuthorFront author;

    public BookFront() {
    }

    public BookFront(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.author = new AuthorFront(book.getAuthor());
    }

    public BookFront(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public AuthorFront getAuthor() {
        return author;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(AuthorFront author) {
        this.author = author;
    }
}
