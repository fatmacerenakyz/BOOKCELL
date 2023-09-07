package tr.com.bookcell.util;

public class InputFormatter {
    public static String capitalizeFirst(String str){
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
    public static String capitalizeForBookName(String str){
        String[] strArr = str.split(" ");
        String finalString = "";
        for(String temp : strArr){
            if(temp.equalsIgnoreCase("and") || temp.equalsIgnoreCase("ve")){
                temp = temp.toLowerCase();
            }
            else{
                temp = temp.substring(0,1).toUpperCase() + temp.substring(1).toLowerCase();
            }
            if(finalString.equals("")){
                finalString = finalString+temp;
            }
            else{
                finalString=finalString+" "+temp;
            }
        }

        return finalString;
    }
    public static String capitalizeForMultipleStrings(String str){
        String[] strArr = str.split(" ");
        String finalString = "";
        for(String temp : strArr){
            temp = temp.substring(0,1).toUpperCase()+temp.substring(1).toLowerCase();
            if(finalString.equals("")) finalString=finalString+temp;
            else finalString=finalString+" "+temp;
        }
        return finalString;
    }
}
