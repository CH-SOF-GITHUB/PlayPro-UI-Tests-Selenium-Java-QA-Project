package org.skilr.com.JavaBasic;

public class AngryDog extends Dog {

    public AngryDog(String name) {
        super(name);
        // extra state on subclass
    }

    @Override
    public void greet() {
        System.out.println("AngryDog says Hi!");
    }

    public void bite() {
        System.out.println(this.getName() + " Grr! Grr!JBITE!I");
    }
}
