package com.ra.bioskop.util;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<PasswordFormat, String> {

    @Override
    public void initialize(PasswordFormat constraintAnnotation) {}

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        return password.length() > 5 && isContainANumber(password);
    }

    private boolean isContainANumber(String password) {
        Pattern pattern = Pattern.compile("\\d");
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
