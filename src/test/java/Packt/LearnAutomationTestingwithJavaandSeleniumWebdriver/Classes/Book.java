package Packt.LearnAutomationTestingwithJavaandSeleniumWebdriver.Classes;

import Packt.LearnAutomationTestingwithJavaandSeleniumWebdriver.Exceptions.InvalidNoOfCopies;

public class Book {

    // define noOfCopies
    int noOfCopies;

    public Book() {
    }

    // Behavior
    public int getNoOfCopies() {
        return noOfCopies;
    }

    public void setNoOfCopies(int noOfCopies) {
        if (noOfCopies < 0) {
            throw new InvalidNoOfCopies("Error to set a negative number");
        }
        this.noOfCopies = noOfCopies;
    }

    public void increaseNbOfCopies(int newNb) {
        this.noOfCopies = this.noOfCopies + newNb;
    }

    public void decreaseNbOfCopies(int newNb) {
        this.noOfCopies = this.noOfCopies - newNb;
    }
}
