package org.skilr.com.JavaBasic;

public abstract class Dog {
    private String name;

    public Dog(String name) {
        this.name = name;
    }

    public void bark() {
        System.out.println(this.name + " says 'Bark! Bark!'");
    }

    public abstract void greet();


    public String getName() {
        return name;
    }
}

