package works.buddy.library.ui;

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

    void sayGoodBye();

    void handleErrorResponse();
}
