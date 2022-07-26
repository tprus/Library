package works.buddy.library.api.view;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Author")
public class AuthorFront {

    private Integer id;

    private String firstName;

    private String lastName;

    public AuthorFront() {
    }

    public AuthorFront(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getId() {
        return id;
    }

    @XmlElement
    public String getFirstName() {
        return firstName;
    }

    @XmlElement
    public String getLastName() {
        return lastName;
    }
}
