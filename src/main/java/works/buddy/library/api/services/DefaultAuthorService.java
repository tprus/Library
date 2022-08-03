package works.buddy.library.api.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import works.buddy.library.api.view.AuthorFront;
import works.buddy.library.api.view.AuthorsFront;
import works.buddy.library.model.Author;

@Service
@Transactional
public class DefaultAuthorService extends AbstractDefaultService implements AuthorService {

    @Override
    public AuthorsFront getAuthors() {
        return getAuthorFronts(authorDAO.findAll());
    }

    @Override
    public AuthorFront getAuthor(Integer authorId) {
        validateAuthorId(authorId);
        return getAuthorFront(authorDAO.findOne(authorId));
    }

    @Override
    public AuthorFront addAuthor(AuthorFront authorFront) {
        validateAuthorFront(authorFront);
        Author author = getAuthor(authorFront);
        authorDAO.save(author);
        return getAuthorFront(author);
    }

    @Override
    public AuthorFront updateAuthor(Integer id, AuthorFront authorFront) {
        validateUpdateAuthor(id, authorFront);
        Author author = authorDAO.findOne(id);
        author.setFirstName(authorFront.getFirstName());
        author.setLastName(authorFront.getLastName());
        return getAuthorFront(author);
    }

    @Override
    public void deleteBook(Integer id) {
        validateAuthorId(id);
        Author returnedObject = authorDAO.findOne(Integer.valueOf(id));
        authorDAO.delete(returnedObject);
    }

}
