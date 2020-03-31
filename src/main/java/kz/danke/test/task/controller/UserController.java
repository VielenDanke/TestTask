package kz.danke.test.task.controller;

import kz.danke.test.task.model.User;
import kz.danke.test.task.security.CustomUserDetails;
import kz.danke.test.task.security.annotations.IsAuthorized;
import kz.danke.test.task.service.MessageService;
import kz.danke.test.task.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final MessageService messageService;

    @ModelAttribute("user")
    public User user() {
        return new User();
    }

    @GetMapping("/")
    public String getMainPage(Model model) {
        model.addAttribute("allMessage", messageService.findAll());
        return "index";
    }

    @GetMapping("/registration")
    public String getRegistrationPage() {
        return "registration";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/cabinet")
    @IsAuthorized
    public ModelAndView getCabinetPage(@AuthenticationPrincipal CustomUserDetails authUser) {
        User user = userService.findByUsername(authUser.getUsername());

        ModelAndView modelAndView = new ModelAndView("cabinet");

        modelAndView.addObject("name", user.getUsername());
        modelAndView.addObject("number", user.getNumber());
        modelAndView.addObject("birthDate", user.getDateOfBirth());
        modelAndView.addObject("imageName", user.getImageName());
        modelAndView.addObject("userMessages", user.getMessages());

        return modelAndView;
    }

    @GetMapping("/increase")
    @IsAuthorized
    public String increaseNumber(@AuthenticationPrincipal CustomUserDetails authUser, Model model) {
        User byUsername = userService.findByUsername(authUser.getUsername());

        model.addAttribute("number", byUsername.getNumber());
        model.addAttribute("name", byUsername.getUsername());
        model.addAttribute("birthDate", byUsername.getDateOfBirth());

        byUsername.setNumber(byUsername.getNumber() + 1);

        userService.save(byUsername);

        return "cabinet";
    }
}
