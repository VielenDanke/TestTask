package kz.danke.test.task.util;

import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;

import java.util.Map;
import java.util.stream.Collectors;

public class ControllerUtil {

    public static Map<String, String> getErrors(BindingResult bindingResult) {
        return bindingResult.getFieldErrors().stream().collect(Collectors.toMap(
                fieldError -> fieldError.getField() + "Error",
                fieldError -> {
                    String defaultMessage = fieldError.getDefaultMessage();
                    if (!StringUtils.isEmpty(defaultMessage)) {
                        return defaultMessage;
                    } else {
                        return "This field is not correct";
                    }
                }
        ));
    }
}
