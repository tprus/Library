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

    public AuthorFront(String firstName) {
        this.firstName = firstName;
    }

    public Integer getId() {
        return id;
    }

    @XmlElement
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
