package works.buddy;


import java.util.ArrayList;
import java.util.List;

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

}
