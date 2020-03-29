package kz.danke.test.task.controller;

import kz.danke.test.task.model.Message;
import kz.danke.test.task.security.CustomUserDetails;
import kz.danke.test.task.service.MessageService;
import kz.danke.test.task.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;
    private final UserService userService;

    @PostMapping("add_message")
    public String addMessage(
            @RequestParam(name = "messageText") String messageText,
            @AuthenticationPrincipal CustomUserDetails authUser
    ) {
        Message message = new Message();

        message.setDescription(messageText);
        message.setUser(userService.findByUsername(authUser.getUsername()));

        messageService.save(message);

        return "redirect:/cabinet";
    }
}
