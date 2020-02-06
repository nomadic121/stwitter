package com.example.servingwebcontent;

import com.example.servingwebcontent.domain.Message;
import com.example.servingwebcontent.repos.MessagesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class IndexController {

    @Autowired
    private MessagesRepo messagesRepo;

    @GetMapping
    public String index(
            Map<String, Object> model) {
        Iterable<Message> messages = messagesRepo.findAll();
        model.put("messages", messages);
        return "index";
    }


    @PostMapping
    public String add(@RequestParam String text, @RequestParam String tag, Map<String, Object> model) {
        Message message = new Message(text, tag);

        messagesRepo.save(message);

        Iterable<Message> messages = messagesRepo.findAll();

        model.put("messages", messages);

        return "index";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        Iterable<Message> messages;

        if (filter != null && !filter.isEmpty()) {
            messages = messagesRepo.findByTag(filter);
        } else {
            messages = messagesRepo.findAll();
        }

        model.put("messages", messages);

        return "index";
    }

}