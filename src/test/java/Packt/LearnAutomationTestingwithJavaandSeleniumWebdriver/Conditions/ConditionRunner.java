package Packt.LearnAutomationTestingwithJavaandSeleniumWebdriver.Conditions;

import Packt.LearnAutomationTestingwithJavaandSeleniumWebdriver.Exceptions.InvalidNumberEntered;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConditionRunner {

    public static void main(String[] args) {
        //If statement condition
        int k = 15;
        puzzle1(k);

        int l = 15;
        puzzle2(l);

        int m = 15;
        puzzle3(m);

        int number = -1;
        puzzle5(number);

        int i = 24;
        basicNestedIfElse(i);

        // Menu Runner
        SimpleCalculator();
    }

    private static void SimpleCalculator() {
        Scanner scanner = new Scanner(System.in);
        try {
            int number1 = getNumber1(scanner);

            int number2 = getNumber2(scanner);

            int choice = getChoice(scanner);

            printEntrees(number1, number2, choice);

            performOperationUsingSwitch(choice, number1, number2);
        } catch (InputMismatchException e) {
            throw new InvalidNumberEntered("Cannot be enter invalid Number", e);
        }
    }

    private static int getNumber1(Scanner scanner) {
        System.out.println("\nEnter number 1 : ");
        int number1 = scanner.nextInt();
        return number1;
    }

    private static int getNumber2(Scanner scanner) {
        System.out.println("Enter number 2 : ");
        int number2 = scanner.nextInt();
        return number2;
    }

    private static int getChoice(Scanner scanner) {
        System.out.println("Choices available are");
        System.out.println("1 - Add");
        System.out.println("2 - Subtract");
        System.out.println("3 - Divide");
        System.out.println("4 - Multiply");
        int choice = scanner.nextInt();
        return choice;
    }

    private static void printEntrees(int number1, int number2, int choice) {
        System.out.println("Your choices are : ");
        System.out.println("Number1 :  " + number1);
        System.out.println("Number2 :  " + number2);
        System.out.println("Choice  :  " + choice);
    }

    private static void performOperationUsingSwitch(int choice, int number1, int number2) {
        switch (choice) {
            case 1:
                System.out.printf("Result : %d", number1 + number2).println();
                break;
            case 2:
                System.out.printf("Result : %d", number1 - number2).println();
                break;
            case 3:
                System.out.printf("Result : %d", number1 / number2).println();
                break;
            case 4:
                System.out.printf("Result : %d", number1 * number2).println();
                break;
            default:
                System.out.println("Your choice is not available !");
        }
    }

    private static void basicNestedIfElse(int i) {
        if (i == 25) {
            System.out.println("i == 25");
        } else if (i == 24) {
            System.out.println("i == 24");
        } else if (i == 23) {
            System.out.println("i == 23");
        } else {
            System.out.println("i!=25 and i!=24 and i!=23");
        }
    }

    private static void puzzle5(int number) {
        if (number < 0) {
            number = number + 10;
        }
        number++;
        System.out.println("number= " + number);
    }

    private static void puzzle3(int m) {
        if (m > 20) {
            if (m < 20) {
                System.out.println("m>20");
            }
        } else {
            System.out.println("How I am?");
        }
    }

    private static void puzzle2(int l) {
        if (l < 20) {
            System.out.println("l<20");
        } else if (l > 20) {
            System.out.println("l>20");
        } else {
            System.out.println("How I am ?");
        }
    }

    private static void puzzle1(int k) {
        if (k > 20) {
            System.out.println(1);
        } else if (k > 10) {
            System.out.println(2);
        } else if (k < 20) {
            System.out.println(3);
        } else {
            System.out.println(4);
        }
    }
}
