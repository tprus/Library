package works.buddy.library.api.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Service
public class MessagesService {

    private static final Logger LOG = LoggerFactory.getLogger(MessagesService.class);

    private static Map<Integer, String> messages;

    private @Value("messages.properties") String messagesPath;

    @PostConstruct
    public void init() {
        messages = loadMessages(messagesPath);
    }

    private Map<Integer, String> loadMessages(String messagePath) {
        Map<Integer, String> messages = new HashMap<>();
        try {
            Properties properties = new Properties();
            properties.load(new ClassPathResource(messagePath).getInputStream());
            for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                messages.put(Integer.parseInt((String) entry.getKey()), (String) entry.getValue());
            }
        } catch (Exception e) {
            LOG.error("Error in loading messages", e);
        }
        return messages;
    }

    public static String getMessage(Integer code, Object... arguments) {
        return MessageFormat.format(messages.get(code).replaceAll("'", "''"), arguments);
    }

}