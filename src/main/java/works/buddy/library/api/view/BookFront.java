package works.buddy.library.api.view;

import works.buddy.library.model.Book;

import java.io.Serializable;

public class BookFront implements Serializable {

    private Long id;

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

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public AuthorFront getAuthor() {
        return author;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(AuthorFront author) {
        this.author = author;
    }
}
