package tr.com.bookcell.util;


import java.util.Scanner;

public class TestClassMethods {

    public static boolean backToMenuWhile(String backToMenuInput, boolean isBackToMenu, boolean yesOrNoAnswer, Scanner scanner) {
        do {
            isBackToMenu = true;
            System.out.println(ansiColorYellowBackGround() + ansiColorBlack() + "WOULD YOU LIKE TO GO BACK TO MENU? (Y/N)" + ansiColorReset());
            backToMenuInput = scanner.nextLine();
            backToMenuInput = backToMenuInput.toUpperCase();
            switch (backToMenuInput) {
                case ("Y") ->
                        System.out.println(ansiColorGreenBackGround() + ansiColorBlack() + "YOU ARE REDIRECTED TO THE MENU." + ansiColorReset());
                case ("N") -> isBackToMenu = false;
                default -> {
                    System.out.println(ansiColorRed() + "ENTER Y OR N!" + ansiColorReset());
                    yesOrNoAnswer = false;
                }
            }
        } while (!yesOrNoAnswer);
        return isBackToMenu;
    }

    public static String ansiColorReset() {
        return "\u001B[0m";
    }

    public static String ansiColorBlack() {
        return "\u001B[30m";
    }

    public static String ansiColorRed() {
        return "\u001B[31m";
    }

    public static String ansiColorGreen() {
        return "\u001B[32m";
    }

    public static String ansiColorYellow() {
        return "\u001B[33m";
    }

    public static String ansiColorDarkBlue() {
        return "\u001B[34m";
    }

    public static String ansiColorMagenta() {
        return "\u001B[35m";
    }

    public static String ansiColorCyan() {
        return "\u001B[36m";
    }

    public static String ansiColorRedBackGround() {
        return "\u001B[41m";
    }

    public static String ansiColorGreenBackGround() {
        return "\u001B[42m";
    }

    public static String ansiColorYellowBackGround() {
        return "\u001B[43m";
    }

    public static String ansiColorDarkBlueBackGround() {
        return "\u001B[44m";
    }

    public static String ansiColorMagentaBackGround() {
        return "\u001B[45m";
    }

    public static String ansiColorCyanBackGround() {
        return "\u001B[46m";
    }

    public static String ansiColorBold() {
        return "\u001B[1m";
    }

    public static String ansiColorItalic() {
        return "\u001B[3m";
    }
}
