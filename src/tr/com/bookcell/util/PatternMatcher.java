package tr.com.bookcell.util;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternMatcher {
    public static Integer emailPattern(String text) {
        String defaultEmailPattern = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,7}\\b";
        String studentEmailPattern = "\\S+@+\\S+\\.edu\\.tr";
        String vipEmailPattern = "\\bturkcell\\.com\\.tr\\b";

        Pattern defaultPattern = Pattern.compile(defaultEmailPattern);
        Pattern studentPattern = Pattern.compile(studentEmailPattern);
        Pattern vipPattern = Pattern.compile(vipEmailPattern);

        Matcher defaultMatcher = defaultPattern.matcher(text);
        Matcher studentMatcher = studentPattern.matcher(text);
        Matcher vipMatcher = vipPattern.matcher(text);

        if (studentMatcher.find()) {
            return 1;
        } else if (vipMatcher.find()) {
            return 2;
        } else if (defaultMatcher.find()) {
            return 0;
        } else {
            return -1;
        }
    }

    public static boolean passwordPattern(String text) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[.,_]).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        System.out.println("Please enter a proper password.");
        return matcher.matches();
    }
}
