package works.buddy.library.api.errors;

import works.buddy.library.api.services.MessagesService;

import java.text.MessageFormat;

public class NotFoundException extends RuntimeException {

    public NotFoundException(Integer messageCode, Object[] parameter) {
        super(MessageFormat.format(MessagesService.getMessage(messageCode), parameter));
    }
}
