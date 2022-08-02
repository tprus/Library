package works.buddy.library.api.errors;

import java.util.ArrayList;
import java.util.Collection;

public class Errors {

    public Collection<Error> errors;

    public Errors() {
    }

    public Errors(Collection<Error> errors) {
        this.errors = errors;
    }

    public Errors addError(Error error) {
        if (errors == null) {
            errors = new ArrayList<>();
        }
        errors.add(error);
        return this;
    }
}
