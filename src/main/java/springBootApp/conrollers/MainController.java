package springBootApp.conrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springBootApp.Services.MessageService;
import springBootApp.domain.MessageEntity;

import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private MessageService messageService;

    private final static String PATH = "/main";

    @GetMapping(PATH)
    public String main(@RequestParam(name="filter", required=false, defaultValue="") String filter,
                       Map<String, Object> model) {
        Iterable<MessageEntity> messages = messageService.messageIterator(filter);
        model.put("messages", messages);
        model.put("messages", messages);
        return "main";
    }


    @PostMapping(PATH)
    public String add(@RequestParam String text, @RequestParam String tag, Map<String, Object> model) {
        messageService.saveMessage(text, tag);
        Iterable<MessageEntity> messages = messageService.messageIterator("");
        model.put("messages", messages);
        return "main";
    }

}