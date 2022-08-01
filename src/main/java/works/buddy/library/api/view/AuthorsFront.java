package works.buddy.library.api.view;

import java.util.Collection;

public class AuthorsFront {

    private Collection<AuthorFront> authors;

    AuthorsFront() {
    }

    public AuthorsFront(Collection<AuthorFront> authors) {
        this.authors = authors;
    }

    public Collection<AuthorFront> getAuthors() {
        return authors;
    }

    public void setAuthors(Collection<AuthorFront> authors) {
        this.authors = authors;
    }
}
