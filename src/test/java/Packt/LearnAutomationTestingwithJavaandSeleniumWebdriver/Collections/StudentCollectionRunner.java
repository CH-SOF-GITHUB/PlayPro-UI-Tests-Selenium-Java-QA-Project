package Packt.LearnAutomationTestingwithJavaandSeleniumWebdriver.Collections;

import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StudentCollectionRunner {

    public static void main(String[] args) {

        // create a list of 3 student objects
        List<Student> listOfStudents = List.of(new Student(1, "Ranga"), new Student(100, "Adam"), new Student(2, "Eve"));
        System.out.println("List of students = " + listOfStudents);

        ArrayList<Student> arrayListOfStudents = new ArrayList<>(listOfStudents);
        System.out.println("Array List of students = " + arrayListOfStudents);

        // SORT Array List
        Collections.sort(arrayListOfStudents);
        System.out.println("Array List of students sorted = " + arrayListOfStudents);

        // compare sorded students List
        Student s1 = new Student(1, "Ranga");
        Student s2 = new Student(100, "Adam");
        Student s3 = new Student(2, "Eve");
        int result = s3.compareTo(s2);
        System.out.println("compare result= " + result);

        // Compare from sorted Students List 2 students
        arrayListOfStudents.sort(new AscendingStudentsCompare());
        System.out.println("AscendingStudentComparator = " + arrayListOfStudents);
    }
}
