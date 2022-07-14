package works.buddy;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookStorage {
    private List<Book> books;

    public BookStorage(){
        books = new ArrayList<>();
        books.add(new Book("1984","Scott Chacon and Ben Straub"));
        books.add(new Book("Thinking in Java", "Bruce Eckel"));
    }

    List<Book> GetAllBooks(){
        return books;
    }
    void ListAllBooksInConsole(){
                for (Book book:books)
            System.out.println("name: " + book._name + ", author: " + book._author);
    }
    void AddBook()
    {
        System.out.println("Enter a name for the book");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        System.out.println("Enter the author of " + name);
        Book bookToAdd = new Book(name,scanner.nextLine());
        books.add(bookToAdd);
    }

}
