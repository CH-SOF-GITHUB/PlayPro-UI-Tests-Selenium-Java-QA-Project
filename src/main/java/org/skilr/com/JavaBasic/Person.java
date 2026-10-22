package org.skilr.com.JavaBasic;

import java.util.Objects;

public class Person {
    //visibility modifiers: private,

    public String name;
    public int age;
    private String address;
    // Referencing other Objects
    private Dog dog;

    // Default constructor
    public Person() {
    }

    // Create a constructor
    public Person(String name, int age, Dog dog) {
        this.name = name;
        this.age = age;
        this.dog = dog;
    }

    // Adding methods to class
    public void haveBirthday() {
        this.age = this.age + 1;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Person cannot have a negative age");
        }
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // The Equals Method with Collections
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else {
            return this.name.equals(((Person) obj).name) && this.age == ((Person) obj).age;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
