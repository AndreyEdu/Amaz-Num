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
                - two natural numbers and a property to search for;
                - two natural numbers and two properties to search for;
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
            if (!numInput[0].matches("\\d+")) {
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
                    for (int i = 0; i < numberTwo; i++) {
                        printPropertiesTwoNumbers(numberOne, numString);
                        numberOne++;
                        numString = Long.toString(numberOne);
                    }
                } else if (numberOne < 0) {
                    System.out.println("The first parameter should be a natural number or zero.");
                } else if (numberTwo < 0) {
                    System.out.println("The second parameter should be a natural number.");
                }
            } else if (numInput.length == 3) {
                numberOne = Long.parseLong(numInput[0]);
                numberTwo = Long.parseLong(numInput[1]);
                numString = numInput[0];
                String property = numInput[2].toLowerCase();
                if (checkException(property)) {
                    for(int i = 0; i < numberTwo;) {
                        if (isProperty(property, numberOne,numString)) {
                            printPropertiesTwoNumbers(numberOne, numString);
                            i++;
                        }
                        numberOne++;
                        numString = String.valueOf(numberOne);
                    }
                } else {
                    System.out.println("The property " + property + " is wrong.");
                    System.out.println("Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, EVEN, ODD]");
                }


            } else if (numInput.length == 4) {
                String[] arr = new String[2];
                numberOne = Long.parseLong(numInput[0]);
                numberTwo = Long.parseLong(numInput[1]);
                numString = numInput[0];
                String propertyOne = numInput[2].toLowerCase();
                String propertyTwo = numInput[3].toLowerCase();
                if (propertyOne.equals("sunny") && propertyTwo.equals("square") || propertyTwo.equals("sunny") && propertyOne.equals("square")) {
                    System.out.println("The request contains mutually exclusive properties: [SQUARE, SUNNY]\n" +
                            "There are no numbers with these properties.");
                    continue;
                } else if (propertyOne.equals("odd") && propertyTwo.equals("even") || propertyTwo.equals("odd") && propertyOne.equals("even")) {
                    System.out.println("The request contains mutually exclusive properties: [ODD, EVEN]\n" +
                            "There are no numbers with these properties.");
                    continue;
                } else if (propertyOne.equals("duck") && propertyTwo.equals("spy") || propertyTwo.equals("duck") && propertyOne.equals("spy")) {
                    System.out.println("The request contains mutually exclusive properties: [ODD, EVEN]\n" +
                            "There are no numbers with these properties.");
                    continue;
                }
                if (checkException(propertyOne) && checkException(propertyTwo)) {
                    for(int i = 0; i < numberTwo;) {
                        if (isProperty(propertyOne, numberOne, numString) && isProperty(propertyTwo, numberOne, numString)) {
                            printPropertiesTwoNumbers(numberOne, numString);
                            i++;
                        }
                        numberOne++;
                        numString = String.valueOf(numberOne);
                    }
                } else if (!checkException(propertyOne) && !checkException(propertyTwo)) {
                    arr[0] = propertyOne;
                    arr[1] = propertyTwo;
                    System.out.println("The properties " + propertyOne + " " + propertyTwo + " are wrong.");
                    System.out.println("Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, EVEN, ODD]");
                } else if (!checkException(propertyOne)) {
                    arr[0] = propertyOne;
                    System.out.println("The property " + propertyOne + " is wrong.");
                    System.out.println("Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, EVEN, ODD]");
                } else if (!checkException(propertyTwo)) {
                    arr[1] = propertyTwo;
                    System.out.println("The property " + propertyTwo + " is wrong.");
                    System.out.println("Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, EVEN, ODD]");
                }
            }
        } while (true);
    }

    protected static boolean checkException(String property) {
        int count = 0;
        String[] propertyArr = {"buzz", "duck", "palindromic", "gapful", "spy", "square", "sunny", "even", "odd"};
        for (String s : propertyArr) {
            if (property.equals(s)) {
                count++;
            }
        }
        return count > 0;
    }

    public static boolean isProperty(String property, long numberOne, String numString) {
        switch (property) {
            case "buzz":
                return isBuzz(numberOne, numString);
            case "duck":
                return isDuck(numString);
            case "palindromic":
                return isPalindromic(numString);
            case "gapful":
                return isGapful(numberOne, numString);
            case "spy":
                return isSpy(numString);
            case "even":
                return isEven(numberOne);
            case "odd":
                return isOdd(numberOne);
            case "sunny":
                return isSunny(numberOne);
            case "square":
                return isSquare(numberOne);
            default:
                System.out.println("The property " + property + " is wrong.");
                System.out.println("Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, EVEN, ODD]");
                break;
        }
        return false;
    }
    public static boolean isNaturalNumber(long number) {
        return number > 0;
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
        return new StringBuilder(numString).reverse().toString().equals(numString);
    }

    public static boolean isGapful(long number, String numString) {
        char[] charString = numString.toCharArray();
        char[] remainder = new char[] {charString[0], charString[charString.length - 1]};
        int remaind = Integer.parseInt(String.valueOf(remainder));
        return numString.length() >= 3 && number % remaind == 0;
    }

    public static boolean isSpy(String numString) {
        long sum = 0;
        long product = 1;
        long[] arr = new long[numString.length()];
        for (int i = 0; i < numString.length(); i++) {
            arr[i] = Long.parseLong(String.valueOf(numString.charAt(i)));
            sum += arr[i];
            product *= arr[i];
        }
        return sum == product;
    }

    public static boolean isSquare(long numberOne) {
        double product;
        double count = 1;
        product = Math.sqrt(numberOne);
        return product % 1 == 0;
    }

    public static boolean isSunny(long numberOne) {
        return isSquare(numberOne + 1);
    }

    public static void printProperties(long number, String numString) {
        System.out.println("Properties of " + number);
        System.out.println("        even: " + isEven(number));
        System.out.println("         odd: " + isOdd(number));
        System.out.println("        buzz: " + isBuzz(number, numString));
        System.out.println("        duck: " + isDuck(numString));
        System.out.println(" palindromic: " + isPalindromic(numString));
        System.out.println("      gapful: " + isGapful(number, numString));
        System.out.println("         spy: " + isSpy(numString));
        System.out.println("      square: " + isSquare(number));
        System.out.println("       sunny: " + isSunny(number));
    }

    public static void printPropertiesTwoNumbers(long numberOne, String numString) {

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
        if (isSpy(numString)) {
            System.out.print(" spy");
        }
        if (isSquare(numberOne)) {
            System.out.print(" square");
        }
        if (isSunny(numberOne)) {
            System.out.print(" sunny");
        }
        System.out.println();
    }
}
