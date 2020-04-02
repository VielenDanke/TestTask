package kz.danke.test.task.controller;

import kz.danke.test.task.model.Message;
import kz.danke.test.task.security.CustomUserDetails;
import kz.danke.test.task.security.annotations.IsAdmin;
import kz.danke.test.task.security.annotations.IsAuthorized;
import kz.danke.test.task.service.MessageService;
import kz.danke.test.task.service.UserService;
import kz.danke.test.task.util.ControllerUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;
    private final UserService userService;

    @PostMapping("add_message")
    @IsAuthorized
    public String addMessage(
            @AuthenticationPrincipal CustomUserDetails authUser,
            @Valid Message message,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtil.getErrors(bindingResult);

            model.mergeAttributes(errors);

            return "cabinet";
        }
        message.setUser(userService.findByUsername(authUser.getUsername()));

        messageService.save(message);

        return "redirect:/cabinet";
    }

    @GetMapping("delete_messages")
    @IsAdmin
    public String deleteAllMessages() {
        messageService.deleteAll();
        return "index";
    }
}
