package works.buddy.library.model;

public class Author {
    private String firstName;
    private String lastName;

    public Author(String firstName, String lastName) {
        if (firstName == null || lastName == null)
            throw new IllegalArgumentException("Author has to have first and last name");
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


}
