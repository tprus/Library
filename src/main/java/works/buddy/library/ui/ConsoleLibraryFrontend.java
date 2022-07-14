package works.buddy.library.ui;

import works.buddy.library.model.Book;

import java.util.Collection;
import java.util.Scanner;

public class ConsoleLibraryFrontend implements LibraryFrontend {

    public static final String WELCOME_MSG = "\nWelcome, select action:\n1. List all books\n2. Add a book to our collection\n3. Exit\n";

    private Scanner scanner;

    public ConsoleLibraryFrontend(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void printMainMenu() {
        System.out.println(WELCOME_MSG);
    }

    @Override
    public String getResponse() {
        return scanner.nextLine();
    }

    @Override
    public void askAuthor() {
        System.out.println("Author: ");
    }

    @Override
    public void askName() {
        System.out.println("Name: ");
    }

    @Override
    public void listBooks(Collection<Book> books) {
        for (Book book : books) {
            System.out.println("Name: " + book.getName() + ", Author: " + book.getAuthor());
        }
    }

    @Override
    public void sayGoodBye() {
        System.out.println("Good bye!");
    }

    @Override
    public void handleErrorResponse() {
        System.out.println("Incorrect answer!");
    }
}
