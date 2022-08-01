package works.buddy.library.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import works.buddy.library.api.view.AuthorFront;
import works.buddy.library.api.view.AuthorsFront;
import works.buddy.library.dao.AuthorDAO;
import works.buddy.library.dao.BookDAO;
import works.buddy.library.model.Author;

import java.util.ArrayList;
import java.util.Collection;

@Service
@Transactional
public class DefaultAuthorService implements AuthorService {

    @Autowired
    @Qualifier("hibernateBookDAO")
    private BookDAO bookDAO;

    @Autowired
    private AuthorDAO authorDAO;

    @Override
    public AuthorsFront getAuthors() {
        return getAuthorFronts(authorDAO.findAll());
    }

    @Override
    public AuthorFront getAuthor(Integer authorId) {
        return getAuthorFront(authorDAO.findOne(authorId));
    }

    @Override
    public void createAuthor(AuthorFront author) {
        authorDAO.save(getAuthor(author));
    }

    private Author getAuthor(AuthorFront author) {
        return new Author(author);
    }

    private AuthorFront getAuthorFront(Author author) {
        return new AuthorFront(author);
    }

    private AuthorsFront getAuthorFronts(Collection<Author> authorsToMap) {
        Collection<AuthorFront> mappedAuthors = new ArrayList<>();
        for (Author author : authorsToMap) {
            mappedAuthors.add(new AuthorFront(author));
        }
        return new AuthorsFront(mappedAuthors);
    }
}
