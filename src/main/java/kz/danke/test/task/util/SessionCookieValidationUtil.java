package kz.danke.test.task.util;

import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Arrays;
import java.util.Optional;

import static kz.danke.test.task.util.ConstantUtil.AUTHORIZATION;
import static kz.danke.test.task.util.ConstantUtil.BEARER;

public class SessionCookieValidationUtil {

    private static final int ZERO_INTEGER = 0;

    public static void sessionCookieTokenSet(HttpServletRequest request,
                                         HttpServletResponse response,
                                         String token) {
        Cookie[] cookies = request.getCookies();

        if (cookies.length == ZERO_INTEGER) {
            HttpSession session = request.getSession();
            session.setAttribute(AUTHORIZATION, BEARER + token);
        } else {
            Cookie cookie = new Cookie(AUTHORIZATION, BEARER + token);
            response.addCookie(cookie);
        }
    }

    public static String sessionCookieTokenGet(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        Optional<Cookie> any = Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals(AUTHORIZATION))
                .findAny();

        if (any.isPresent()) {
            return any.get().getValue();
        } else {
            return (String) request.getSession().getAttribute(AUTHORIZATION);
        }
    }
}
