package tr.com.bookcell.util;

import tr.com.bookcell.user.customer.CustomerType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternMatcher {
    public static CustomerType emailPattern(String text){
        String defaultEmailPattern = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,7}\\b";
        String studentEmailPattern = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.edu\\.tr\\b";
        String vipEmailPattern = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.turkcell\\.com\\.tr\\b";

        Pattern defaultPattern = Pattern.compile(defaultEmailPattern);
        Pattern studentPattern = Pattern.compile(studentEmailPattern);
        Pattern vipPattern = Pattern.compile(vipEmailPattern);

        Matcher defaultMatcher = defaultPattern.matcher(text);
        Matcher studentMatcher = studentPattern.matcher(text);
        Matcher vipMatcher = vipPattern.matcher(text);

        if(defaultMatcher.find()){
            return CustomerType.DEFAULT;
        }
        else if(studentMatcher.find()){
            return CustomerType.STUDENT;
        }
        else if(vipMatcher.find()){
            return CustomerType.VIP;
        }
        else{
            System.out.println("email address was not found.");
            return null;
        }

    }
}
