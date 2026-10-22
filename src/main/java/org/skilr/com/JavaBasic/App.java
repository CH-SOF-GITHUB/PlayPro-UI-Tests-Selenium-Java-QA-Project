package org.skilr.com.JavaBasic;

import java.security.InvalidParameterException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class App {

    //main method where program is executed
    public static void main(String[] args) throws InvalidParameterExceptions {
        //System.out.println("Hello World!");
        // Primitive Types
        // assign a value to age variable
        // int age = 32;
        // age = 24;
        //int amount = 1000;
        //int year = 5;

        //double temperature = 78.4;
        //temperature = 78.0;
        //double interestRate = 1.5;

        //boolean isFrozen = true;

        //String name = "Math";
        //Class Types
        //Person me = new Person();
        //me.name = name;
        //System.out.println(me.name + " is " + me.age + " , and lives in Gabes city which The temperature is " + me.temperature + "°C");

        // Expressions
        //int a = 12;
        //int b = 3;
        //int added = a + b;
        //int substructed = a - b;

        //double x = 2.0;
        //double y = 3.0;
        //double multiplied = x * y;
        //double divided = x / y;

        //boolean inStock = false;
        //boolean hasPaid = true;
        //boolean requiresRefund = hasPaid && !inStock;
        //System.out.println("requiresRefund = " + requiresRefund);
        // Logical Operators
        //boolean x = true, y = false;
        //boolean result = x || y;
        //System.out.println("result = " + result);
        // Use expressions to work out the total amount payable by calculating the amounts payable at the end of each year.
        // Example, what is 100$ with 10% interest added ? answer: 110$
        int amount = 100;
        int year = 5;
        double interestRate = 10.0;
        //System.out.println("year1AmountDue = " + year1AmountDue);
        //System.out.println("year2AmountDue = " + year2AmountDue);
        //System.out.println("year3AmountDue = " + year3AmountDue);
        //System.out.println("year4AmountDue = " + year4AmountDue);
        //System.out.println("year5AmountDue = " + year5AmountDue);

        //STRINGS
        //String name = "Chaker";
        //String greeting = "HELLo, " + name + ", How are you ?";
        //System.out.println(greeting.trim());

        // Challenge Part 4: Print out the input values,  Format and print out the final
        //result (to decimal places)
        //System.out.println("Loan Calculator\n".toUpperCase());
        //System.out.println("Calculating loan based on:");
        //System.out.println("Initial Amount:  " + amount);
        //System.out.println("Number Of Years:  " + year);
        //System.out.println("Interest Rate:  " + interestRate + "%");
        //System.out.println("year1AmountDue = " + year1AmountDue);
        //System.out.println("year2AmountDue = " + year2AmountDue);
        //System.out.println("year3AmountDue = " + year3AmountDue);
        //System.out.println("year4AmountDue = " + year4AmountDue);
        //System.out.println("year5AmountDue = " + year5AmountDue);
        //String totalAmountDue = Double.toString(year5AmountDue);
        //int indexOfDecimalPoint = totalAmountDue.indexOf(".");
        //String totalAmountDateFormatted = totalAmountDue.substring(0, indexOfDecimalPoint + 3);
        //System.out.println("Total Amount Due:  " + totalAmountDateFormatted);

        // CONDITIONS
        //boolean isHappy = true;
        //boolean isRich = false;
        //System.out.println("this is before ...");
        //if (isHappyAndRich(isHappy, isRich)) {
        //    System.out.println("You're fulfilled!");
        //} else if (isHappyButPoo(isHappy, isRich)) {
        //    System.out.println("Well it's good - money isn't everything");
        //} else {
        //    System.out.println("That's too bad!");
        //}
        //System.out.println("this is after ...");

        /* ************/
       /* if (isHappy & isRich) {
            System.out.println("You're fulfilled");
            System.out.println("That's good");
        } else if (ishappy && isRich) {
            System.out.println("Well that's good - money isn't everything");
        }else{
            System.out.println("nothing matched!");
        }*/
        /*if (isRich) {
            if(isHappy) {
                System.out.println("You're fulfilled");
                System.out.println("That's good");
            }
        } else if (!isRich) {
            if(isHappy) {
                System.out.println("Well that's good - money isn't everything");
            }
        }else{
            System.out.println("nothing matched!");
        }*/

        /*if (isHappy) {
            if (isRich) {
                System.out.println("You're fulfilled");
                System.out.println("That's good");
            } else if (!isRich) {
                System.out.println("Well that's good - money isn't everything");
            }
        } else {
            System.out.println("nothing matched!");
        }
*/

        //int choice = 1;
        //switch (choice) {
        //    case 1:
        //        System.out.println("You choose option 1 - Load the file!");
        //        break;
        //    case 2:
        //        System.out.println("You choose option 2 - Save the file!");
        //        break;
        //    case 3:
        //        System.out.println("You choose option 3 - Quitting - bye!");
        //        break;
        //    default:
        //        System.out.println("Try again!");
        //}

        // Challenge Part 5 - Only do the calculation if all values are bigger than 0, otherwise print an error message to
        // the user
        /*if(amount > 0 && year > 0 && interestRate > 0) {
            System.out.println("Loan Calculator\n".toUpperCase());
            System.out.println("Calculating loan based on:");
            System.out.println("Initial Amount:  " + amount);
            System.out.println("Number Of Years:  " + year);
            System.out.println("Interest Rate:  " + interestRate + "%");

            double interestRateMultiplier = 1 + interestRate / 100;
            double year1AmountDue = amount * interestRateMultiplier;
            double year2AmountDue = year1AmountDue * interestRateMultiplier;
            double year3AmountDue = year2AmountDue * interestRateMultiplier;
            double year4AmountDue = year3AmountDue * interestRateMultiplier;
            double year5AmountDue = year4AmountDue * interestRateMultiplier;

            System.out.println("year1AmountDue = " + year1AmountDue);
            System.out.println("year2AmountDue = " + year2AmountDue);
            System.out.println("year3AmountDue = " + year3AmountDue);
            System.out.println("year4AmountDue = " + year4AmountDue);
            System.out.println("year5AmountDue = " + year5AmountDue);
            String totalAmountDue = Double.toString(year5AmountDue);
            int indexOfDecimalPoint = totalAmountDue.indexOf(".");
            String totalAmountDateFormatted = totalAmountDue.substring(0, indexOfDecimalPoint + 3);
            System.out.println("Total Amount Due:  " + totalAmountDateFormatted);
        } else {
            System.out.println("Invalid Values - cannot calculate repayment amount");
        }*/

        // LOOPS:
        int i = 1;
        /*While Loop
        while(i <= 5) {
            System.out.println("i = " + i);
            i++;
        }*/
        /*Do While Loop
        do {
            System.out.println("i = " + i);
            i++;
        }while(i <= 5);*/
        /*Esoteric Loops
        for (int j = 10; j < 1000; j = j * 10) {
            System.out.println("J = " + j);
        }*/
        /*For Each Loop with Collections */
        List<String> food = new ArrayList<>();
        food.add("sag aloo");
        food.add("mushroom rice");
        food.add("lamb vindaloo");
        /*
        for (String item : food) {
            System.out.println("Food = " + item);
        }*/
        /*// Processing Collections with Streams
        Set<String> filteredFoods = food.stream()
                .map(f -> f.toUpperCase())
                .filter(f -> f.contains("RICE"))
                .collect(Collectors.toSet());
        System.out.println("filteredFoods = " + filteredFoods);*/

        // Challenge Part 6 - replace the expressions-based way of the calculating interest with a while-loop way
        /*if (amount > 0 && year > 0 && interestRate > 0) {
            printInputs(amount, year, interestRate);
            double interestRateMultiplier = 1 + interestRate / 100;
            double currentRepaymentAmount = calculateRepaymentAmount(amount, year, interestRateMultiplier);
            printResult(currentRepaymentAmount);
        } else {
            System.out.println("Invalid Values - cannot calculate repayment amount");
        }*/

        // Challenge Part 7 - Extract out the different logically-related chunks of code into
        // their own well-named methods
        /*if (amount > 0 && year > 0 && interestRate > 0) {
            printInputs(amount, year, interestRate);
            double interestRateMultiplier = 1 + interestRate / 100;
            double currentRepaymentAmount = calculateRepaymentAmount(amount, year, interestRateMultiplier);
            printResult(currentRepaymentAmount);
        } else {
            System.out.println("Invalid Values - cannot calculate repayment amount");
        }*/

        // CLASSs:
        // Objects References
        // FriendlyDog dog = new FriendlyDog("Daisy");
        // Class Person
        //Person me = new Person("Matt", 35, dog);
        //System.out.println("Matt's Age : " + me.getAge());
        //me.setAge(37);
        //System.out.println("After,Matt's Age : " + me.getAge());

        // Superclass Variable references
        //Dog fido = new Dog("Fido");
        //fido.bark();

        FriendlyDog daisy = new FriendlyDog("Daisy");
        daisy.lick();
        daisy.bark();
        daisy.greet();

        AngryDog einstein = new AngryDog("Einstein");
        einstein.bite();
        einstein.greet();

        System.out.println("\n\n");

        // Polymorphism in action
        List<Dog> dogs = new LinkedList<>();
        dogs.add(daisy);
        dogs.add(einstein);
        dogs.add(new YappyDog("Valentino"));

        for (Dog dog : dogs) {
            dog.greet();
        }

        System.out.println("\n\n");

        // Challenge Part 8 - Adding a class - Pull out the calculation logic into its own class,
        // LoanCalculator (take the variables along for the ride tool)
        // Pushing Logic into a Class to keep Code Clean
        /*if (amount > 0 && year > 0 && interestRate > 0) {
            printInputs(amount, year, interestRate);
            double interestRateMultiplier = 1 + interestRate / 100;
            LoanCalculator calculator = new LoanCalculator(amount, year, interestRateMultiplier);
            double currentRepaymentAmount = calculator.calculateRepaymentAmount();
            printResult(currentRepaymentAmount);
        } else {
            System.out.println("Invalid Values - cannot calculate repayment amount");
        }*/

        // EXCEPTIONS:
        String reqQty = "10!";
        //Validate(reqQty);

        try {
            Date d = new SimpleDateFormat("dd-MM-yyyy").parse("01-01-2021");
            PlanetaryBody pb = Utils.parsePlanetaryBody("saturn");
            System.out.println(" d = " + d);
        } catch (PlanetaryBodyUnparseableException e) {
            System.out.println("Could not parse planetary body.");
        } catch (ParseException e) {
            System.out.println("Could not parse the date.");
        }

        // Challenge Part 9 - Dealing With Bad Input by Introducing Exception Handling
        /*try {
            printInputs(amount, year, interestRate);
            double interestRateMultiplier = 1 + interestRate / 100;
            LoanCalculator calculator = new LoanCalculator(amount, year, interestRateMultiplier);
            double currentRepaymentAmount = calculator.calculateRepaymentAmount();
            printResult(currentRepaymentAmount);
        } catch (LoanCalculatorException e) {
            System.out.println(e.getMessage());
        }*/

        // COLLECTIONS: List, HasSet
        List<String> list = new ArrayList<>();
        list.add("strawberries");
        list.add("oranges");
        list.add("bananas");
        System.out.println("List = " + list);
        System.out.println("List's Size = " + list.size());
        list.add(2, "Apple");
        System.out.println("List = " + list);

        Map<String, String> favouriteFoods = new HashMap<>();
        favouriteFoods.put("fruits", "strawberries");
        favouriteFoods.put("pizzas", "pepperoni");
        favouriteFoods.put("cheeses", "bleu d'auvergne");

        System.out.println(favouriteFoods.get("fruits"));

        // Implementing Hash Code and Equals
        Set<Person> people = new HashSet<>();
        people.add(new Person("Matt", 30, null));
        people.add(new Person("Susan", 28, null));
        people.add(new Person("Brian", 56, null));
        boolean containsMatt = people.contains(new Person("Matt", 30, null));
        System.out.println("containsMatt = " + containsMatt);
    }

    /*private static void Validate(String reqQty) {
        try {
            int qty = Integer.parseInt(reqQty);
            System.out.println("qty = " + qty + " and All parameters are valid!");
        } catch (NumberFormatException e) {
            throw new InvalidParameterExceptions(reqQty + "is not a valid value for an integer", e);
        }
    }*/


    // Add External Methods: Refactoring Methods
    /*private static void printResult(double currentAmountPayable) {
        String totalAmountDue = Double.toString(currentAmountPayable);
        int indexOfDecimalPoint = totalAmountDue.indexOf(".");
        String totalAmountDateFormatted = totalAmountDue.substring(0, indexOfDecimalPoint + 3);
        System.out.println("Total Amount Due: $" + totalAmountDateFormatted);
    }*/

/*private static void printInputs(int amount, int year, double interestRate) {
    System.out.println("Loan Calculator\n".toUpperCase());
    System.out.println("Calculating loan based on:");
    System.out.println("Initial Amount:  " + amount);
    System.out.println("Number Of Years:  " + year);
    System.out.println("Interest Rate:  " + interestRate + "%");
}*/

    /*private static boolean isHappyButPoo(boolean isHappy, boolean isRich) {
        return isHappy && !isRich;
    }

    private static boolean isHappyAndRich(boolean isHappy, boolean isRich) {
        return isHappy && isRich;
    }*/

}
