package kz.danke.test.task.controller;

import kz.danke.test.task.model.User;
import kz.danke.test.task.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ModelAttribute("user")
    public User user() {
        return new User();
    }

    @GetMapping("/")
    public String getMainPage() {
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
    public ModelAndView getCabinetPage(Principal principal) {
        User user = userService.findByUsername(principal.getName());

        ModelAndView modelAndView = new ModelAndView("cabinet");

        modelAndView.addObject("name", user.getUsername());
        modelAndView.addObject("number", user.getNumber());
        modelAndView.addObject("birthDate", user.getDateOfBirth());

        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView saveUser(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView("/login");

        userService.save(user);

        return modelAndView;
    }

    @GetMapping("/increase")
    public String increaseNumber(Principal principal, Model model) {
        User byUsername = userService.findByUsername(principal.getName());

        model.addAttribute("number", byUsername.getNumber());
        model.addAttribute("name", byUsername.getUsername());
        model.addAttribute("birthDate", byUsername.getDateOfBirth());

        byUsername.setNumber(byUsername.getNumber() + 1);

        userService.save(byUsername);

        return "cabinet";
    }
}
