package springBootApp.conrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springBootApp.domain.MessageEntity;
import springBootApp.repos.MessagesRepo;

import java.util.Map;

@Controller
public class MainController {

    //TODO move messageRepo from here
    @Autowired
    private MessagesRepo messageRepo;

    private final static String PATH = "/main";

    @GetMapping(PATH)
    public String main(@RequestParam(name="filter", required=false, defaultValue="") String filter,
                       Map<String, Object> model) {
        Iterable<MessageEntity> messages;
        if (filter != null && !filter.isEmpty()) {
            messages = messageRepo.findByTag(filter);
        } else {
            messages = messageRepo.findAll();
        }
        model.put("messages", messages);
        model.put("messages", messages);
        return "main";
    }


    @PostMapping(PATH)
    public String add(@RequestParam String text, @RequestParam String tag, Map<String, Object> model) {
        MessageEntity message = new MessageEntity(text, tag);

        messageRepo.save(message);

        Iterable<MessageEntity> messages = messageRepo.findAll();

        model.put("messages", messages);

        return "main";
    }

//    @PostMapping("filter")
//    public String filter(@RequestParam(name="filter", required=false, defaultValue="") String filter, Map<String, Object> model) {
//        Iterable<MessageEntity> messages;
//
//        if (filter != null && !filter.isEmpty()) {
//            messages = messageRepo.findByTag(filter);
//        } else {
//            messages = messageRepo.findAll();
//        }
//
//        model.put("messages", messages);
//
//        return "main";
//    }

    Object obj = new Object();


}