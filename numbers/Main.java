package numbers;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("""
                Welcome to Amazing Numbers!
                
                Supported requests:
                - enter a natural number to know its properties;
                - enter two natural numbers to obtain the properties of the list:
                  * the first parameter represents a starting number;
                  * the second parameter shows how many consecutive numbers are to be printed;
                - separate the parameters with one space;
                - enter 0 to exit.
                
                """);

        Scanner scanner = new Scanner(System.in);
        long numberOne;
        long numberTwo;
        String numString;

        do {
            System.out.print("Enter a request: ");
            numString = scanner.nextLine();
            String[] numInput = numString.split(" ");
            if (numInput[0].equals("exit")) {
                System.out.println("The first parameter should be a natural number or zero.");
                continue;
            } else if (numInput[0].equals("0")) {
                System.out.println("Goodbye!");
                break;
            }
            if (numInput.length == 1) {
                numberOne = Long.parseLong(numInput[0]);
                if (numberOne > 0) {
                    printProperties(numberOne, numString);
                } else {
                    System.out.println("The first parameter should be a natural number or zero.");
                }
            } else if (numInput.length == 2) {
                numberOne = Long.parseLong(numInput[0]);
                numberTwo = Long.parseLong(numInput[1]);
                numString = numInput[0];
                if (numberOne > 0 && numberTwo > 0) {
                    printPropertiesTwoNumbers(numberOne, numString, numberTwo);
                } else if (numberOne < 0) {
                    System.out.println("The first parameter should be a natural number or zero.");
                } else if (numberTwo < 0) {
                    System.out.println("The second parameter should be a natural number.");
                }
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
        char[] arrayStart = numString.toCharArray();
        int count = 0;
        for (int i = numString.length() - 1; i != -1; i--) {
            arrayEnd[count] = numString.charAt(i);
            count++;
        }
        return Arrays.equals(arrayEnd, arrayStart);
    }

    public static boolean isGapful(long number, String numString) {
        char[] charString = numString.toCharArray();
        char[] remainder = new char[] {charString[0], charString[charString.length - 1]};
        int remaind = Integer.parseInt(String.valueOf(remainder));
        return numString.length() >= 3 && number % remaind == 0;
    }

    public static void printProperties(long number, String numString) {
        System.out.println("Properties of " + number);
        System.out.println("        even: " + isEven(number));
        System.out.println("         odd: " + isOdd(number));
        System.out.println("        buzz: " + isBuzz(number, numString));
        System.out.println("        duck: " + isDuck(numString));
        System.out.println(" palindromic: " + isPalindromic(numString));
        System.out.println("      gapful: " + isGapful(number, numString));
    }

    public static void printPropertiesTwoNumbers(long numberOne, String numString, long numberTwo) {
        for (int i = 0; i < numberTwo; i++) {
            System.out.print(numberOne + " is");
            if (isBuzz(numberOne, numString)) {
                System.out.print(" buzz");
            }
            if (isDuck(numString)) {
                System.out.print(" duck");
            }
            if (isPalindromic(numString)) {
                System.out.print(" palindromic");
            }
            if (isGapful(numberOne, numString)) {
                System.out.print(" gapful");
            }
            if (isEven(numberOne)) {
                System.out.print(" even");
            }
            if (isOdd(numberOne)) {
                System.out.print(" odd");
            }
            numberOne++;
            numString = Long.toString(numberOne);
            System.out.println();
        }
    }
}
