package by.bsuir.recourse.validation.validator;

import by.bsuir.recourse.entity.dto.PasswordChanging;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class PasswordChangingValidator implements Validator{
    @Override
    public boolean supports(Class<?> aClass) {
        return PasswordChanging.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        PasswordChanging passwordChanging = (PasswordChanging) o;
        if (!passwordChanging.getPassword().equals(passwordChanging.getPasswordConfirmation())){
            errors.rejectValue("password", "password", "Passwords do not match");
        }
    }
}
