package numbers;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("""
                Welcome to Amazing Numbers!
                
                Supported requests:
                - enter a natural number to know its properties;
                - enter 0 to exit.
                
                """);

        Scanner scanner = new Scanner(System.in);
        long number;
        String numString;

        do {
            System.out.print("Enter a request: ");
            number = scanner.nextLong();
            numString = Long.toString(number);

            if (number < 0) {
                System.out.println("The first parameter should be a natural number or zero.");
            } else if (number == 0) {
                System.out.println("Goodbye!");
                break;
            } else {
                printProperties(number, numString);
            }

        } while (true);
    }

    public static boolean isEven(long number) {
        return number % 2 == 0;
    }

    public static boolean isOdd(long number) {
        return !(number % 2 == 0);
    }

    public static boolean isBuzz(long number, String numString) {
        return number % 7 == 0 || numString.charAt(numString.length() - 1) == '7';
    }

    public static boolean isDuck(String numString) {
        boolean duck = false;
        for (int i = 1; i <= numString.length() - 1; i++) {
            if (numString.charAt(i) == '0') {
                duck = true;
                break;
            }
        }
        return duck;
    }

    public static boolean isPalindromic(String numString) {
        char[] arrayEnd = new char[numString.length()];
        char[] arrayStart = new char[numString.length()];
        int count = 0;
        for (int i = numString.length() - 1; i != -1; i--) {
            arrayEnd[count] = numString.charAt(i);
            count++;
        }
        for (int i = 0; i < numString.length(); i++) {
            arrayStart[i] = numString.charAt(i);
        }
        return Arrays.equals(arrayEnd, arrayStart);
    }

    public static void printProperties(long number, String numString) {
        System.out.println("Properties of " + number);
        System.out.println("        even: " + isEven(number));
        System.out.println("         odd: " + isOdd(number));
        System.out.println("        buzz: " + isBuzz(number, numString));
        System.out.println("        duck: " + isDuck(numString));
        System.out.println(" palindromic: " + isPalindromic(numString));
    }
}
