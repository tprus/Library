package works.buddy;


import java.io.Console;
import java.util.List;
import java.util.Scanner;

public class Main {
    static BookStorage storage = new BookStorage();

    public static void main(String[] args) {
        StringBuilder welcomeMessageBuilder = new StringBuilder();
        welcomeMessageBuilder.append("Welcome, select action:\n")
                        .append("1. List all books");

        System.out.println(welcomeMessageBuilder.toString());
        Scanner input = new Scanner(System.in);
        String test = input.nextLine();
        outer: while(true){
            switch (test){
                case "1":
                    storage.ListAllBooksInConsole();
                    break;
                case "exit":
                    break outer;
                default:
                    System.out.println("Select a proper action, or exit by typing \"exit\"");
            }
            test = input.nextLine();
        };
        System.out.println("Thanks for stopping by");
    }
}
