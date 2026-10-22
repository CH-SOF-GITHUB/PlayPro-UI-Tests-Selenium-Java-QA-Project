package Packt.LearnAutomationTestingwithJavaandSeleniumWebdriver.Classes;

import lombok.Getter;

import java.util.Arrays;

public class Student {

    // state
    private String name;
    private int[] marks;

    // constructor
    public Student(String name, int[] marks) {
        this.name = name;
        this.marks = marks;
    }

    public Student(String name) {
        this.name = name;
    }

    public Student() {
    }

    // behavior
    public int getNumberOfMarks() {
        return marks.length;
    }

    public int getTotalSumOfMarks() {
        int sum = 0;
        for (int mark : marks) {
            sum = sum + mark;
        }
        return sum;
    }

    public int getMaximumMark() {
        // SECURITY
        if (marks == null || marks.length == 0) {
            return 0;
        }

        int maximumMark = Integer.MIN_VALUE;
        for (int mark : marks) {
            if (mark > maximumMark) {
                maximumMark = mark;
            }
        }
        return maximumMark;
    }

    public int getMinimumMark() {
        // SECURITY
        if (marks == null || marks.length == 0) {
            return 0;
        }

        int minimumMark = Integer.MAX_VALUE;
        for (int mark : marks) {
            if (mark < minimumMark) {
                minimumMark = mark;
            }
        }
        return minimumMark;
    }

    public double getAverageMarks() {
        return marks.length > 0 ? (double) getTotalSumOfMarks() / getNumberOfMarks() : 0.0;
    }


    /* Getter and Setter */
    public int[] getMarks() {
        return marks;
    }

    public void setMarks(int[] marks) {
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", marks=" + Arrays.toString(marks) +
                '}';
    }
}
