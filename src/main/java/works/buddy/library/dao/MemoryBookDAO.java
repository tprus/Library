package works.buddy.library.dao;

import works.buddy.library.model.Author;
import works.buddy.library.model.Book;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

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
        Author author = book.getAuthor();
        if(author == null) {
            throw new IllegalArgumentException("Author name cannot be null");
        }
        int lastNameLength = author.getLastName().length();
        int firstNameLength = author.getFirstName().length();
        int bookNameLength = book.getName().length();
        if (lastNameLength < MIN_AUTHOR_NAME || firstNameLength < MIN_AUTHOR_NAME) {
            throw new IllegalArgumentException(String.format("Author name cannot be shorter than %d characters", MIN_AUTHOR_NAME));
        }
        if (lastNameLength > MAX_AUTHOR_NAME || firstNameLength > MAX_AUTHOR_NAME) {
            throw new IllegalArgumentException(String.format("Author name cannot be longer than %d characters", MAX_AUTHOR_NAME));
        }
        if (bookNameLength < MIN_AUTHOR_NAME) {
            throw new IllegalArgumentException(String.format("Book name cannot be shorter than %d characters", MIN_AUTHOR_NAME));
        }
        if (bookNameLength > MAX_AUTHOR_NAME ) {
            throw new IllegalArgumentException(String.format("Book name cannot be longer than %d characters", MAX_AUTHOR_NAME));
        }
        if (books.contains(book)){
            throw new IllegalArgumentException("Book with this id already exists");
        }
        this.books.add(book);
    }

    @Override
    public Collection<Book> getBooks() {
        ArrayList<Book> sortedBooks = new ArrayList<>(books);
        Collections.sort(sortedBooks, new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                if (o1.getAuthor().getLastName().compareTo(o2.getAuthor().getLastName()) == 0 ){ return 0;}
                if (o1.getAuthor().getLastName().compareTo(o2.getAuthor().getLastName()) < 0) {
                    return -1;
                }
                else {
                    return 1;
                }
            }
        });
        return sortedBooks;
    }
}