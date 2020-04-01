package kz.danke.test.task.controller;

import kz.danke.test.task.model.Message;
import kz.danke.test.task.security.CustomUserDetails;
import kz.danke.test.task.security.annotations.IsApprovedPerson;
import kz.danke.test.task.security.annotations.IsAuthorized;
import kz.danke.test.task.service.MessageService;
import kz.danke.test.task.service.UserService;
import kz.danke.test.task.util.ControllerUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;
    private final UserService userService;

    @PostMapping("add_message")
    @IsAuthorized
    public String addMessage(
            @RequestParam(name = "messageText") String messageText,
            @AuthenticationPrincipal CustomUserDetails authUser,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtil.getErrors(bindingResult);
            model.mergeAttributes(errors);
            return "cabinet";
        }
        Message message = new Message();

        message.setDescription(messageText);
        message.setUser(userService.findByUsername(authUser.getUsername()));

        messageService.save(message);

        return "redirect:/cabinet";
    }
}
