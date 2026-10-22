package Packt.LearnAutomationTestingwithJavaandSeleniumWebdriver.Classes;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    // Méthode pour ajouter une note avec System.arraycopy()
    public void addMark(int newMark) {
        // 1. Créer un tableau plus grand de 1
        int[] newMarks = new int[marks.length + 1];

        // 2. Copier l'ancien tableau dans le nouveau en une seule ligne
        System.arraycopy(marks, 0, newMarks, 0, marks.length);

        // 3. Ajouter la nouvelle note à la fin
        newMarks[newMarks.length - 1] = newMark;

        // 4. Mettre à jour la référence
        this.marks = newMarks;

        System.out.println("Add New Mark : " + newMark);
    }

    // Suppression par INDEX (ex: supprimer le 1er élément)
    public void removeMarkByIndex(int index) {
        if (index > 0 && index < marks.length) {
            List<Integer> list = new ArrayList<>();
            for (int mark : marks) {
                list.add(mark);
            }

            list.remove(index);

            this.marks = list.stream().mapToInt(Integer::intValue).toArray();

            System.out.println("Remove mark at index : " + index);
        }
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
