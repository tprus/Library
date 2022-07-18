package works.buddy.library.dao;

import works.buddy.library.model.Book;

import java.util.Collection;

public class MemoryBookDAO implements BookDAO {

    private static final int MIN_AUTHOR_NAME = 5;

    private static final int MAX_AUTHOR_NAME = 100;

    private Collection<Book> books;

    public MemoryBookDAO(Collection<Book> books) {
        if (books == null) {
            throw new IllegalArgumentException("Parameter books cannot be null");
        }
        this.books = books;
    }

    @Override
    public void save(Book book) {
        String author = book.getAuthor();
        if(author == null) {
            throw new IllegalArgumentException("Author name cannot be null");
        }
        int length = author.length();
        if (length < MIN_AUTHOR_NAME) {
            throw new IllegalArgumentException(String.format("Author name cannot be shorter than %d characters", MIN_AUTHOR_NAME));
        }
        if (length > MAX_AUTHOR_NAME) {
            throw new IllegalArgumentException(String.format("Author name cannot be longer than %d characters", MAX_AUTHOR_NAME));
        }
        this.books.add(book);
    }

    @Override
    public Collection<Book> getBooks() {
        return books;
    }
}