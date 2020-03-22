package kz.danke.test.task.exceptions.handler;

import kz.danke.test.task.exceptions.WrongDateException;
import kz.danke.test.task.model.User;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice {

    @ExceptionHandler(WrongDateException.class)
    public String exceptionHandler(HttpServletRequest request, Exception ex) {
        request.setAttribute("error", ex.getMessage());
        return "errorPage";
    }
}
