package works.buddy.library.app.console.services;

import works.buddy.library.model.Author;
import works.buddy.library.model.Book;

import java.util.Collection;

public interface LibraryFrontend {

    void printMainMenu();

    String getResponse();

    void askAuthorFirstName();

    void askAuthorLastName();

    void askBookName();

    void askId();

    void listBooks(Collection<Book> books);

    void listAuthor(Author author);

    void sayGoodBye();

    void handleErrorResponse();
}
