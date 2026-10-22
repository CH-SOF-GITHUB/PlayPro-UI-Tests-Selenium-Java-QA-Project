package Packt.LearnAutomationTestingwithJavaandSeleniumWebdriver.Exceptions;

public class InvalidNumberEntered extends IllegalArgumentException {
    public InvalidNumberEntered(String message, Throwable cause) {
        super(message, cause);
    }
}
