package kz.danke.test.task.controller;

import kz.danke.test.task.dto.LoginRequest;
import kz.danke.test.task.model.User;
import kz.danke.test.task.security.jwtoken.TokenProvider;
import kz.danke.test.task.service.UserService;
import kz.danke.test.task.util.ControllerUtil;
import kz.danke.test.task.util.FileUploadUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final FileUploadUtil fileUploadUtil;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;

    @Value("${spring.servlet.multipart.location}")
    private String filePath;

    @ModelAttribute("user")
    public User user() {
        return new User();
    }

    @GetMapping("/registration")
    public String getRegistrationPage() {
        return "registration";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(
            HttpServletRequest request,
            @Valid LoginRequest loginRequest,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtil.getErrors(bindingResult);

            model.mergeAttributes(errors);

            return "login";
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.createToken(authentication);

        request.getSession().setAttribute("Authorization", "Bearer " + token);

        return "index";
    }

    @PostMapping("/save")
    public String saveUser(@Valid User user,
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
