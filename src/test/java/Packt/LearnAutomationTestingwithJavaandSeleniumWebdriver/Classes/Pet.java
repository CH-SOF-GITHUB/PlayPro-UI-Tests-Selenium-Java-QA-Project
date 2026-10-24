package Packt.LearnAutomationTestingwithJavaandSeleniumWebdriver.Classes;

public class Pet extends Animal {

    public void groom() {
        System.out.println("Groom");
    }

    @Override
    public void walk() {
        System.out.println("Pet walk ... !");
    }


}
