package org.skilr.com.JavaBasic;

public class YappyDog extends FriendlyDog{

    public YappyDog(String name) {
        super(name);
    }

    public void greet(){
        lick();
        super.greet();
    }
}
