package kz.danke.test.task.controller;

import kz.danke.test.task.model.User;
import kz.danke.test.task.service.UserService;
import kz.danke.test.task.util.FileUploadUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final FileUploadUtil fileUploadUtil;

    @Value("${spring.servlet.multipart.location}")
    private String filePath;

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
}
