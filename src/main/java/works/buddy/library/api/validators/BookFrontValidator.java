package works.buddy.library.api.validators;

import works.buddy.library.api.errors.BadRequestException;
import works.buddy.library.api.view.AuthorFront;
import works.buddy.library.api.view.BookFront;

public class BookFrontValidator {

    private static final int MIN_TITLE_LENGTH = 3;

    private static final int MAX_TITLE_LENGTH = 100;

    private static final String TITLE = "title";

    private static final String AUTHOR = "author";

    private static final String AUTHOR_ID = "author.id";

    public static void validateBookId(Integer id) {
        if (id == null) {
            throw new BadRequestException(2, "id");
        }
    }

    public static void validateBookFront(BookFront book) {
        if (book.getTitle() == null) {
            throw new BadRequestException(2, TITLE);
        }
        int titleLength = book.getTitle().length();
        if (titleLength < MIN_TITLE_LENGTH) {
            throw new BadRequestException(102, MIN_TITLE_LENGTH);
        }
        if (titleLength > MAX_TITLE_LENGTH) {
            throw new BadRequestException(103, MAX_TITLE_LENGTH);
        }
        AuthorFront author = book.getAuthor();
        if (author == null) {
            System.out.println("elo");
            throw new BadRequestException(2, AUTHOR);
        }
        if (author.getId() == null) {
            throw new BadRequestException(2, AUTHOR_ID);
        }
    }
}
