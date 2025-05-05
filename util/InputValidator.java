package util;

import java.util.regex.Pattern;

public class InputValidator {
    private static final Pattern emailPattern =
            Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

    public static boolean isValidEmail(String email) {
        return email != null && emailPattern.matcher(email).matches();
    }

    public static boolean isValidPassword(String password) {
        return password != null && password.length() >= 6;
    }
}