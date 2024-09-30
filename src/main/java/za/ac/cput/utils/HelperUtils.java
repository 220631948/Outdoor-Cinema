package za.ac.cput.utils;

import org.springframework.expression.ParseException;

import jakarta.validation.constraints.Pattern;

public class HelperUtils {

    // Check if a string is null or empty
    public static boolean isNullOrEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }

    // check if a int is null or empty
    public static boolean isNullOrEmpty(Integer i) {
        return i == null || i == 0;
    }

    // check if a double is null or empty
    public static boolean isNullOrEmpty(Double d) {
        return d == null || d == 0.0;
    }

    // Check if a email is valid
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Invalid email format")
    public static boolean isValidEmail(String email) {
        return !isNullOrEmpty(email);
    }

    // Check if a number is null or zero
    public static boolean isNullOrZero(Integer i) {
        return i == null || i == 0;
    }

    public static boolean isValidReference(String reference) {
        return reference != null && reference.matches("^[A-Za-z0-9]{5,50}$");
    }

    // Validate if a string is a valid date (format: yyyy-MM-dd)
    // public static boolean isValidDate(String dateStr) {
    // if (isNullOrEmpty(dateStr)) {
    // return false;
    // }
    // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    // sdf.setLenient(false);
    // try {
    // sdf.parse(dateStr);
    // return true;
    // } catch (ParseException e) {
    // return false;
    // }
    // }

    // Validate if a string is a valid time (format: HH:mm)
    // public static boolean isValidTime(String timeStr) {
    // // if (isNullOrEmpty(timeStr)) {
    // // return false;
    // // }
    // // SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
    // // sdf.setLenient(false);
    // // try {
    // // sdf.parse(timeStr);
    // // return true;
    // // } catch (ParseException e) {
    // // return false;
    // // }
    // }

    // // Validate if a string is a valid email
    // public static boolean isValidEmail(String email) {
    // if (isNullOrEmpty(email)) {
    // return false;
    // }
    // String emailRegex =
    // "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    // Pattern pat = Pattern.compile(emailRegex);
    // return pat.matcher(email).matches();
    // }

    // // Validate if a string is a valid phone number (simple validation)
    // public static boolean isValidPhoneNumber(String phoneNumber) {
    // // if (isNullOrEmpty(phoneNumber)) {
    // // return false;
    // // }
    // // String phoneRegex = "^[0-9]{10,15}$";
    // // Pattern pat = Pattern.compile(phoneRegex);
    // // return pat.matcher(phoneNumber).matches();
    // // }
    // return false;
    // }

}
