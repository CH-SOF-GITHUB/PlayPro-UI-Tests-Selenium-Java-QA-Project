package Packt.LearnAutomationTestingwithJavaandSeleniumWebdriver.Classes;

public class MotorBike {
    // state
    String name;
    int speed;

    int currentGear;

    public MotorBike(String name) {
        this.name = name;
    }

    public MotorBike() {
    }

    // Behavior
    void start() {
        System.out.println("\n" + this.name + " Bike started !");
    }


    public void increaseSpeed(int newSpeed) {
        this.speed = this.speed + newSpeed;
        System.out.println(this.name + " increase his speed by " + newSpeed + " km");
    }

    public void decreaseSpeed(int newSpeed) {
        this.speed = this.speed - newSpeed;
        System.out.println(this.name + " decrease his speed by " + newSpeed + " Km");
    }

    public void nextGear() {
        this.currentGear = this.currentGear + 1;
    }

    public void prevGear() {
        this.currentGear = this.currentGear - 1;
    }

    @Override
    public String toString() {
        return "MotorBike [currentSpeed=" + speed + ", currentGear=" + currentGear + "]";
    }

    // Getter and Setter
    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getCurrentGear() {
        return currentGear;
    }

    public void setCurrentGear(int currentGear) {
        this.currentGear = currentGear;
    }
}
