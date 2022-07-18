package works.buddy.library.services;

import works.buddy.library.dao.BookDAO;
import works.buddy.library.model.Author;
import works.buddy.library.model.Book;
import works.buddy.library.ui.LibraryFrontend;

public class ConsoleBookManager {
    private BookDAO bookDAO;

    private LibraryFrontend libraryFrontend;

    public ConsoleBookManager(BookDAO bookDAO, LibraryFrontend libraryFrontend) {
        this.bookDAO = bookDAO;
        this.libraryFrontend = libraryFrontend;
    }

    public void run() {
        printMainMenu();
        String response = getResponse();
        while (true) {
            switch (response) {
                case "1":
                    libraryFrontend.listBooks(bookDAO.getBooks());
                    break;
                case "2":
                    addBook();
                    break;
                case "3":
                    libraryFrontend.sayGoodBye();
                    return;
                default:
                    libraryFrontend.handleErrorResponse();
            }
            printMainMenu();
            response = getResponse();
        }
    }

    private void printMainMenu() {
        libraryFrontend.printMainMenu();
    }

    private String getResponse() {
        return libraryFrontend.getResponse();
    }

    private void addBook() {
        libraryFrontend.askId();
        Integer id = Integer.valueOf(getResponse());
        libraryFrontend.askBookName();
        String bookName = getResponse();
        libraryFrontend.askAuthorFirstName();
        String firstName = getResponse();
        libraryFrontend.askAuthorLastName();
        bookDAO.save(new Book(id, bookName, new Author(firstName,getResponse())));
    }
}
