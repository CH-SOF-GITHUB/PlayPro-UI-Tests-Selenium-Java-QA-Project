package Packt.LearnAutomationTestingwithJavaandSeleniumWebdriver.Classes;

public class Multiply {
    private int number;

    public Multiply(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    // Method: printing table and making it more generic
    public void printMultiplicationTable() {
        for (int i = 1; i <= 10; i++) {
            printMultiplicationResult(number, i);
        }
    }

    private void printMultiplicationResult(int number, int counter) {
        System.out.printf("%d * %d = %d", number, counter, number * counter).println();
    }
}
