package Packt.LearnAutomationTestingwithJavaandSeleniumWebdriver.Classes;

import java.util.Arrays;

public class StudentRunner {

    public static void main(String[] args) {
        Student ranga = new Student("Ranga");

        // set marks of ranga
        ranga.setMarks(new int[]{97, 98, 100});
        // Print Student
        System.out.println(ranga.toString());
        // calcul
        int number = ranga.getNumberOfMarks();
        System.out.println("number of marks : " + number);

        int sum = ranga.getTotalSumOfMarks();
        System.out.println("sum of marks : " + sum);

        int maxMark = ranga.getMaximumMark();
        System.out.println("maximum of marks : " + maxMark);

        int minMark = ranga.getMinimumMark();
        System.out.println("minimum of marks : " + minMark);

        double average = ranga.getAverageMarks();
        System.out.println("average of marks : " + average);

        System.out.println("\n");

        ranga.addMark(35);
        System.out.println(ranga.toString());
        number = ranga.getNumberOfMarks();
        System.out.println("number of marks again : " + number);

        System.out.println("\n");

        ranga.addMark(55);
        System.out.println(ranga.toString());
        number = ranga.getNumberOfMarks();
        System.out.println("number of marks again : " + number);

        System.out.println("\n");

        ranga.removeMarkByIndex(3);
        System.out.println(ranga.toString());
        number = ranga.getNumberOfMarks();
        System.out.println("number of marks after remove element : " + number);
    }
}
