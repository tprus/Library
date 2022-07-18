package works.buddy.library.dao;

import works.buddy.library.model.Book;

import java.util.Collection;

public class MemoryBookDAO implements BookDAO {

    private Collection<Book> books;

    public MemoryBookDAO(Collection<Book> books) {
        if (books == null) {
            throw new IllegalArgumentException("Parameter book cannot be null");
        }
        this.books = books;
    }

    @Override
    public void save(Book book) {
        if ( book.getAuthor().length() < 5 ) {throw new IllegalArgumentException("Parameter cannot be shorter than 5 characters");}
        if (book != null ) {
            this.books.add(book);
        }
    }

    @Override
    public Collection<Book> getBooks() {
        return books;
    }
}