package Packt.LearnAutomationTestingwithJavaandSeleniumWebdriver.Classes;

public class Aeroplane {

    // state
    int currentSpeed;

    @Override
    public String toString() {
        return "Aeroplane{" +
                "currentSpeed=" + currentSpeed +
                '}';
    }

    // Behavior
    public void travelToAirport() {
        System.out.println("Travel to the airport");
    }

    public void findCheckInCounter() {
        System.out.println("Find the check-in counter");
    }

    public void checkIn() {
        System.out.println("Check in");
    }

    public void passSecurityCheck() {
        System.out.println("Pass the security check");
    }

    public void waitForBoardingCall() {
        System.out.println("Wait for the boarding call");
    }

    public void boardTheFlight() {
        System.out.println("Board the flight");
    }

    public void wishTheAirHostess() {
        System.out.println("Wish the air hostess");
    }

    public void takeOff() {
        System.out.println("The airplane takes off");
    }

    public void haveFun() {
        System.out.println("Enjoy the flight");
    }

    public void landing() {
        System.out.println("The airplane lands");
    }/**/

    // Speeds
    public void increaseSpeed(int speed) {
        currentSpeed = getCurrentSpeed() + speed;
    }

    public void decreaseSpeed(int speed) {
        currentSpeed = getCurrentSpeed() - speed;
    }

    // Getter and Setter
    public int getCurrentSpeed() {
        return currentSpeed;
    }

    public void setCurrentSpeed(int currentSpeed) {
        this.currentSpeed = currentSpeed;
    }
}
