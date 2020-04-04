package kz.danke.test.task.util;

import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Arrays;
import java.util.Base64;
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
            token = BEARER.concat(token);
            String tokenAfterSerialization = serialize(token);
            Cookie cookie = new Cookie(AUTHORIZATION, tokenAfterSerialization);
            response.addCookie(cookie);
        }
    }

    public static String sessionCookieTokenGet(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        Optional<Cookie> authorizationCookie = Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals(AUTHORIZATION))
                .findAny();

        if (authorizationCookie.isPresent()) {
            return deserialize(authorizationCookie.get());
        } else {
            return (String) request.getSession().getAttribute(AUTHORIZATION);
        }
    }

    private static String serialize(String token) {
        return Base64.getUrlEncoder()
                .encodeToString(SerializationUtils.serialize(token));
    }

    private static String deserialize(Cookie cookie) {
        return (String) SerializationUtils.deserialize(
                Base64.getUrlDecoder().decode(cookie.getValue()));
    }
}
