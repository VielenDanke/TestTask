package kz.danke.test.task.controller;

import kz.danke.test.task.model.User;
import kz.danke.test.task.service.UserService;
import kz.danke.test.task.util.ControllerUtil;
import kz.danke.test.task.util.FileUploadUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final FileUploadUtil fileUploadUtil;

    @Value("${spring.servlet.multipart.location}")
    private String filePath;

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User user,
                           BindingResult bindingResult,
                           Model model) throws IOException {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtil.getErrors(bindingResult);
            model.mergeAttributes(errors);
            return "registration";
        }

        user.setImageName(fileUploadUtil.fileUpload(user.getMultipartFile(), filePath));

        userService.save(user);

        return "/login";
    }

    @GetMapping("/activate/{code}")
    public String activateAccount(HttpServletRequest request, @PathVariable String code) {
        User user = userService.findByActivationCode(code);

        request.getSession().setAttribute("activate", "Successfully activated");

        userService.save(user);

        return "redirect:/login";
    }
}
