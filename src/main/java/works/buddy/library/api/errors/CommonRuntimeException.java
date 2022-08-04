package works.buddy.library.api.errors;

import works.buddy.library.api.services.MessagesService;

import java.text.MessageFormat;

public abstract class CommonRuntimeException extends RuntimeException {

    protected CommonRuntimeException(Integer messageCode, Object... params) {
        super(MessageFormat.format(MessagesService.getMessage(messageCode), params));
    }
}
