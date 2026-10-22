package Packt.LearnAutomationTestingwithJavaandSeleniumWebdriver.Methods;

import Packt.LearnAutomationTestingwithJavaandSeleniumWebdriver.Classes.Max;
import Packt.LearnAutomationTestingwithJavaandSeleniumWebdriver.Classes.Multiply;

public class MultiplicationTable {

    public static void main(String[] args) {

        // object of class Multiply
        Multiply m = new Multiply(5);
        m.printMultiplicationTable();

        // object of class Max
        int nbr1 = 5;
        int nbr2 = 15;
        Max max = new Max(nbr1, nbr2);
        int max1 = max.searchMax();
        max.printMaxResult(max1);

        double x = 34.59;
        double y = 22.00;
        Max maxDb = new Max(x, y);
        int max2 = maxDb.searchMax();
        maxDb.printMaxResult(max2);
    }
}
