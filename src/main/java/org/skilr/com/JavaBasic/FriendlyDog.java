package org.skilr.com.JavaBasic;

public class FriendlyDog extends Dog {

    // Constructor
    public FriendlyDog(String name) {
        super(name);
    }

    @Override
    public void greet() {
        System.out.println("FriendlyDog says Hi!");
    }

    // Adding custom methods
    public void lick() {
        System.out.println(this.getName() + " Lick! Lick!");
    }
}
