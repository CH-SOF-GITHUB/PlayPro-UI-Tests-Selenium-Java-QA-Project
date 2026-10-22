package Packt.LearnAutomationTestingwithJavaandSeleniumWebdriver.Classes;

public class AeroplaneRunner {

    public static void main(String[] args) {
        Aeroplane aeroplane1 = new Aeroplane();
        aeroplane1.currentSpeed = 500;

        Aeroplane aeroplane2 = new Aeroplane();
        aeroplane2.currentSpeed = 0;

        Aeroplane aeroplane3 = new Aeroplane();
        aeroplane3.currentSpeed = 600;

        aeroplane2.currentSpeed = 300;

        aeroplane3.currentSpeed = 1000;

        //Global Data
        /*fly() {
            travelToAirport();
            findCheckInCounter();
            checkIn();
            passSecurityCheck();
            waitForBoardingCall();
            boardTheFlight();
            wishTheAirHostess();
            takeOff();
            haveFun();
            landing();
        }*/
        aeroplane3.travelToAirport();
        aeroplane3.findCheckInCounter();
        aeroplane3.checkIn();
        aeroplane3.passSecurityCheck();
        aeroplane3.waitForBoardingCall();
        aeroplane3.boardTheFlight();
        aeroplane3.wishTheAirHostess();
        aeroplane3.takeOff();
        System.out.println("Aeroplane 3 Current Speed :   " + aeroplane3.currentSpeed);
        aeroplane3.haveFun();
        aeroplane3.decreaseSpeed(500);
        aeroplane3.decreaseSpeed(400);
        aeroplane3.decreaseSpeed(50);
        System.out.println(aeroplane3.toString());
        aeroplane3.landing();
    }
}
