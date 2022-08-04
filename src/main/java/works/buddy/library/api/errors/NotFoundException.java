package works.buddy.library.api.errors;

public class NotFoundException extends CommonRuntimeException {

    public NotFoundException(Integer messageCode, Object... params) {
        super(messageCode, params);
    }
}
