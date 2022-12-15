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
                    System.out.println("Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, EVEN, ODD, JUMPING]");
                }


            } else {
                numberOne = Long.parseLong(numInput[0]);
                numberTwo = Long.parseLong(numInput[1]);
                numString = numInput[0];
                int numOfProperties = numInput.length;
                if (propertyWrong(numInput) && noNumbers(numInput)) {
                    for (int i = 0; i < numberTwo;) {
                        int check = 0;
                        for (int j = 2; j < numOfProperties; j++) {
                            if (isProperty(numInput[j], numberOne, numString)) {
                                check++;
                            }
                        }
                        if (check == numInput.length - 2) {
                            printPropertiesTwoNumbers(numberOne, numString);
                            i++;
                        }
                        numberOne++;
                        numString = String.valueOf(numberOne);
                    }
                }
            }
        } while (true);
    }

    protected static boolean checkException(String property) {
        int count = 0;
        String[] propertyArr = {"buzz", "duck", "palindromic", "gapful", "spy", "square", "sunny", "even", "odd", "jumping"};
        for (String s : propertyArr) {
            if (property.equals(s)) {
                count++;
            }
        }
        return count > 0;
    }

    protected static boolean propertyWrong(String[] numInput) {
        int count = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i < numInput.length; i++) {
            if(!isProperty(numInput[i].toLowerCase())) {
                sb.append(numInput[i]).append(" ");
                count++;
            }
        }
        if (count == 1) {
            sb.insert(0, "The property ");
            sb.append(" is wrong");
            System.out.println(sb);
            System.out.println("Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, EVEN, ODD, JUMPING]");
            return false;
        } else if (count > 1) {
            sb.insert(0, "The properties ");
            sb.append(" are wrong");
            System.out.println(sb);
            System.out.println("Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, EVEN, ODD, JUMPING]");
            return false;
        } else {
            return true;
        }
    }

    protected static boolean noNumbers(String[] numInput) {
        int countDS = 0;
        int countSS = 0;
        int countEO = 0;
        for (int i = 2; i < numInput.length; i++) {
            switch (numInput[i]) {
                case "duck", "spy" -> countDS++;
                case "even", "odd" -> countEO++;
                case "sunny", "square" -> countSS++;
            }
        }
        if (countDS == 2) {
            System.out.println("The request contains mutually exclusive properties: [DUCK, SPY]\n" +
                    "There are no numbers with these properties.");
            return false;
        }
        if (countEO == 2) {
            System.out.println("The request contains mutually exclusive properties: [EVEN, ODD]\n" +
                    "There are no numbers with these properties.");
            return false;
        }
        if (countSS == 2) {
            System.out.println("The request contains mutually exclusive properties: [SQUARE, SUNNY]\n" +
                    "There are no numbers with these properties.");
            return false;
        }
        return true;
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
            case "jumping":
                return isJumping(numberOne, numString);
        }
        return false;
    }

    public static boolean isProperty(String property) {
        return switch (property) {
            case "buzz", "palindromic", "duck", "gapful", "even", "spy", "odd", "sunny", "square", "jumping" -> true;
            default -> false;
        };
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

    public static boolean isJumping(long numberOne, String numString) {
        if (numString.length() == 1) {
            return true;
        }
        long[] array = new long[numString.length()];
        for (int i = 0; i < numString.length(); i++) {
            array[i] = numberOne % 10;
            numberOne /= 10;
        }
        for (int i = 1; i <= array.length - 1; i++) {
            if (array[i] - 1 == array[i - 1] || array[i] + 1 == array[i - 1]) {

            } else {
                return false;
            }
        }
        return true;
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
        System.out.println("     jumping: " + isJumping(number, numString));
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
        if (isJumping(numberOne, numString)) {
            System.out.print(" jumping");
        }
        System.out.println();
    }
}
