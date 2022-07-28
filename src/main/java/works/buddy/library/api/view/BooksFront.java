package works.buddy.library.api.view;

import java.util.Collection;

public class BooksFront {

    private Collection<BookFront> books;

    public BooksFront() {
    }

    public Collection<BookFront> getBooks() {
        return books;
    }

    public void setBooks(Collection<BookFront> books) {
        this.books = books;
    }

    public BooksFront(Collection<BookFront> books) {
        this.books = books;
    }

}
