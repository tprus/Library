package works.buddy.library.api.errors;

import works.buddy.library.api.services.MessagesService;

import java.text.MessageFormat;

public class BadRequestException extends RuntimeException {

    public BadRequestException(Integer messageCode, Object... params) {
        super(MessageFormat.format(MessagesService.getMessage(messageCode), params));
    }
}
