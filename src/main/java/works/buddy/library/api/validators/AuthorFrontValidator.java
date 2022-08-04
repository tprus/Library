package works.buddy.library.api.validators;

import works.buddy.library.api.errors.BadRequestException;
import works.buddy.library.api.view.AuthorFront;

public class AuthorFrontValidator {

    private static final int MIN_NAME_LENGTH = 3;

    private static final int MAX_NAME_LENGTH = 100;

    private static final String FIRSTNAME = "firstName";

    private static final String LASTNAME = "lastName";

    private static final String ID = "id";

    public static void validateAuthorFront(AuthorFront author) {
        String firstName = author.getFirstName();
        if (firstName == null) {
            throw new BadRequestException(2, FIRSTNAME);
        }
        int firstNameLength = firstName.length();
        if (firstNameLength < MIN_NAME_LENGTH) {
            throw new BadRequestException(104, MIN_NAME_LENGTH);
        }
        if (firstNameLength > MAX_NAME_LENGTH) {
            throw new BadRequestException(105, MAX_NAME_LENGTH);
        }
        String lastName = author.getLastName();
        if (lastName == null) {
            throw new BadRequestException(2, LASTNAME);
        }
        int lastNameLength = lastName.length();
        if (lastNameLength < MIN_NAME_LENGTH) {
            throw new BadRequestException(106, MIN_NAME_LENGTH);
        }
        if (lastNameLength > MAX_NAME_LENGTH) {
            throw new BadRequestException(107, MAX_NAME_LENGTH);
        }
    }

    public static void validateAuthorId(Integer id) {
        if (id == null) {
            throw new BadRequestException(2, ID);
        }
    }
}
