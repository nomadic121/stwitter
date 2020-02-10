package springBootApp.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import springBootApp.domain.MessageEntity;
import springBootApp.repos.MessagesRepo;

@Service
public class MessageService {

    @Autowired
    private MessagesRepo messageRepo;

    public void saveMessage(final String text, final String tag) {
        MessageEntity message = new MessageEntity(text, tag);
        messageRepo.save(message);
    }

    public Iterable<MessageEntity> messageIterator (final String filter) {
        Iterable<MessageEntity> messages;
        if (filter != null && !filter.isEmpty()) {
            messages = messageRepo.findByTag(filter);
        } else {
            messages = messageRepo.findAll();
        }
        return  messages;
    }

}
