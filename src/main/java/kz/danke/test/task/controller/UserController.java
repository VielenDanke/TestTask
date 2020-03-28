package kz.danke.test.task.controller;

import kz.danke.test.task.model.User;
import kz.danke.test.task.service.UserService;
import kz.danke.test.task.util.FileUploadUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final FileUploadUtil fileUploadUtil;

    @Value("${spring.servlet.multipart.location}")
    private String filePath;

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
        modelAndView.addObject("imageName", user.getImageName());

        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView saveUser(@ModelAttribute("user") User user) throws IOException {
        ModelAndView modelAndView = new ModelAndView("/login");

        user.setImageName(fileUploadUtil.fileUpload(user.getMultipartFile(), filePath));

        userService.save(user);

        return modelAndView;
    }

    @GetMapping("/activate/{code}")
    public String activateAccount(HttpServletRequest request, @PathVariable String code) {
        User user = userService.findByActivationCode(code);
        request.getSession().setAttribute("activate", "Successfully activated");
        userService.save(user);
        return "redirect:/login";
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
