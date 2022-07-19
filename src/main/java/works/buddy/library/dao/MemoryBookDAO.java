package works.buddy.library.dao;

import works.buddy.library.model.Author;
import works.buddy.library.model.Book;

import java.util.ArrayList;
import java.util.Collection;

public class MemoryBookDAO implements BookDAO {

    private static final int MIN_NAME_LENGTH = 5;

    private static final int MAX_NAME_LENGTH = 100;

    private Collection<Book> books;

    public MemoryBookDAO(Collection<Book> books) {
        if (books == null) {
            throw new IllegalArgumentException("Parameter books cannot be null");
        }
        this.books = books;
    }

    @Override
    public void save(Book book) {
        validate(book);
        this.books.add(book);
    }

    private void validate(Book book) {
        validateNotNull(book.getId(), "Id");
        if (books.contains(book)) {
            throw new IllegalArgumentException("Book with this id already exists");
        }
        validateName(book.getTitle(), "Title");
        Author author = book.getAuthor();
        if (author == null) {
            throw new IllegalArgumentException("Author cannot be null");
        }
        validateName(author.getFirstName(), "Author first name");
        validateName(author.getLastName(), "Author last name");
    }

    private void validateName(String value, String fieldName) {
        validateNotNull(value, fieldName);
        int length = value.length();
        if (length < MIN_NAME_LENGTH) {
            throw new IllegalArgumentException(String.format("%s cannot be shorter than %d characters", fieldName, MIN_NAME_LENGTH));
        }
        if (length > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(String.format("%s cannot be longer than %d characters", fieldName, MAX_NAME_LENGTH));
        }
    }

    private void validateNotNull(Object value, String fieldName) {
        if(value == null) {
            throw new IllegalArgumentException(String.format("%s is required", fieldName));
        }
    }

    @Override
    public Collection<Book> getBooks() {
        ArrayList<Book> sortedBooks = new ArrayList<>(books);
        sortedBooks.sort((o1, o2) -> Integer.compare(o1.getAuthor().getLastName().compareTo(o2.getAuthor().getLastName()), 0));
        return sortedBooks;
    }
}