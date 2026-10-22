package Packt.LearnAutomationTestingwithJavaandSeleniumWebdriver.Classes;

public class MotorBikeRunner {

    public static void main(String[] args) {
        MotorBike ducati = new MotorBike("Ducati");
        MotorBike honda = new MotorBike("Honda");

        ducati.start();
        ducati.setSpeed(100);
        System.out.printf("Ducati speed : %d ", ducati.getSpeed()).println();
        ducati.increaseSpeed(10);
        System.out.printf("New Ducati speed : %d ", ducati.getSpeed()).println();
        ducati.setCurrentGear(3);
        ducati.nextGear();
        ducati.nextGear();
        System.out.println(ducati.toString());


        honda.start();
        honda.setSpeed(80);
        System.out.printf("Honda speed : %d ", honda.getSpeed()).println();
        honda.decreaseSpeed(60);
        System.out.printf("New Honda speed : %d ", honda.getSpeed()).println();
        honda.setCurrentGear(7);
        honda.nextGear();
        honda.nextGear();
        honda.nextGear();
        System.out.println(honda.toString());

        /*ducati.speed = 100;
        honda.speed = 80;

        ducati.speed = 20;
        honda.speed = 0;*/

        /*ducati.setSpeed(20);
        honda.setSpeed(0);*/
    }
}
