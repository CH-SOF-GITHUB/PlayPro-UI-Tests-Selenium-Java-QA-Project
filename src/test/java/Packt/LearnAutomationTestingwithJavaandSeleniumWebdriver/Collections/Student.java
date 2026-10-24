package Packt.LearnAutomationTestingwithJavaandSeleniumWebdriver.Collections;

import org.jspecify.annotations.NonNull;

public class Student implements Comparable<Student> {

    // state
    private int id;
    private String name;

    // constructor
    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getter and Setter

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int compareTo(@NonNull Student o) {
        return Integer.compare(o.id, this.id);
    }
}

