package works.buddy.library;

import java.util.Collection;
import java.util.Scanner;

public class ConsoleBookManager {

    public static final String WELCOME_MSG = "\nWelcome, select action:\n1. List all books\n2. Add a book to our collection\n3. Exit\n";

    private BookDAO bookDAO;

    private Scanner scanner;

    public ConsoleBookManager(BookDAO bookDAO, Scanner scanner) {
        this.bookDAO = bookDAO;
        this.scanner = scanner;
    }

    public void run() {
        printMainMenu();
        String test = getResponse();
        while (true) {
            switch (test) {
                case "1":
                    listBooks(bookDAO.getBooks());
                    break;
                case "2":
                    addBook();
                    break;
                case "3":
                    sayGoodBye();
                    return;
                default:
                    handleErrorResponse();
            }
            printMainMenu();
            test = getResponse();
        }
    }

    private void printMainMenu() {
        System.out.println(WELCOME_MSG);
    }

    private void addBook() {
        askName();
        String name = getResponse();
        askAuthor();
        bookDAO.save(new Book(name, getResponse()));
    }

    private String getResponse() {
        return scanner.nextLine();
    }

    private void askAuthor() {
        System.out.println("Author: ");
    }

    private void askName() {
        System.out.println("Name: ");
    }

    private void listBooks(Collection<Book> books) {
        for (Book book : books) {
            System.out.println("Name: " + book.getName() + ", Author: " + book.getAuthor());
        }
    }

    private void sayGoodBye() {
        System.out.println("Good bye!");
    }

    private void handleErrorResponse() {
        System.out.println("Incorrect answer!");
    }
}
