package works.buddy.library.api.errors;

import works.buddy.library.api.services.MessagesService;

import java.text.MessageFormat;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(Integer messageCode, Object parameter) {
        super(MessageFormat.format(MessagesService.getMessage(messageCode), parameter));
    }
}
