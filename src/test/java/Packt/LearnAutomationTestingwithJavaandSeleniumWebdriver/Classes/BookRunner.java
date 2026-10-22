package Packt.LearnAutomationTestingwithJavaandSeleniumWebdriver.Classes;

public class BookRunner {

    public static void main(String[] args) {
        Book artOfComputerProgramming = new Book();
        Book effectiveJava = new Book();
        Book cleanCode = new Book();

        /*
        artOfComputerProgramming.noOfCopies = 100;
        effectiveJava.noOfCopies = 50;
        cleanCode.noOfCopies = 45;*/

        artOfComputerProgramming.setNoOfCopies(100);
        effectiveJava.setNoOfCopies(50);
        cleanCode.setNoOfCopies(45);
    }
}
