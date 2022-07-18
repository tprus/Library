package works.buddy.library.dao;

import works.buddy.library.model.Book;

import java.util.ArrayList;
import java.util.Collection;

public class MemoryBookDAO implements BookDAO {

    private Collection<Book> books;

    public MemoryBookDAO(Collection<Book> books) {
        this.books = books;
        if (books == null)
            this.books = new ArrayList<Book>();
    }

    @Override
    public void save(Book book) {
        if (book != null)
            this.books.add(book);
    }

    @Override
    public Collection<Book> getBooks() {
        return books;
    }
}