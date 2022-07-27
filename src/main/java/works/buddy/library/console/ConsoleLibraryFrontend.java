package works.buddy.library.console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import works.buddy.library.model.Book;

import java.util.Collection;
import java.util.Scanner;

@Service
public class ConsoleLibraryFrontend implements LibraryFrontend {

    public static final String WELCOME_MSG = "\nWelcome, select action:\n1. List all books\n2. List books by author ID\n3. List books by author\n4. List books by title\n5. Add a book to our collection\nexit. Exit\n";

    @Autowired
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
    public void askAuthorFirstName() {
        System.out.println("Authors first name: ");
    }

    public void askAuthorLastName() {
        System.out.println("Authors last name: ");
    }

    @Override
    public void askBookName() {
        System.out.println("Name: ");
    }

    @Override
    public void askId() {
        System.out.println("ID ");
    }

    @Override
    public void listBooks(Collection<Book> books) {
        for (Book book : books) {
            System.out.println(book);
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
