package utilSystem;

import java.util.*;

public class SystemMethod {
    

    public static boolean isYesNo (String message) {
        Scanner inYesNo = new Scanner(System.in);
        String inputString;

        System.out.print("\n" + message + " (y/n) : ");
        inputString = inYesNo.next();

        while(!inputString.equalsIgnoreCase("y") && !inputString.equalsIgnoreCase("n")) {
            System.err.println("Input harus kata y atau n");
            System.out.print("\n" + message + " (y/n) ");
            inputString = inYesNo.next();
        }
        return inputString.equalsIgnoreCase("y");
    }

    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            // } else if (System.getProperty("os.name").contains("Mac")) {
            //     new ProcessBuilder("clear"); For Mac OS
            } else {
                System.out.println("\033\143");
            }
        } catch (Exception e) {
            System.err.println("System can't clear screen");
        }
    }
}
