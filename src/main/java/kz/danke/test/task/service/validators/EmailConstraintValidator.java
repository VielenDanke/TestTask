package kz.danke.test.task.service.validators;

import kz.danke.test.task.service.annotation.constraints.EmailConstraint;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailConstraintValidator implements ConstraintValidator<EmailConstraint, String> {

   private static final String EMAIL_PATTERN = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
   private static final int EMAIL_LENGTH = 254;

   public void initialize(EmailConstraint constraint) {
   }

   public boolean isValid(String email, ConstraintValidatorContext context) {
      if (StringUtils.isEmpty(email) || email.length() > EMAIL_LENGTH) {
         return false;
      }

      Pattern validEmail = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);

      Matcher emailMatcher = validEmail.matcher(email);

      return emailMatcher.find();
   }
}
