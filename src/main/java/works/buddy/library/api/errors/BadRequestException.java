package works.buddy.library.api.errors;

public class BadRequestException extends CommonRuntimeException {

    public BadRequestException(Integer messageCode, Object... params) {
        super(messageCode, params);
    }
}
