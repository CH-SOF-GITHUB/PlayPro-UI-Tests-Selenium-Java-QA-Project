package Packt.LearnAutomationTestingwithJavaandSeleniumWebdriver.Classes;

public class Max {

    private static double a;
    private static double b;

    public Max(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public double getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public Integer searchMax() {
        return (int) Math.max(a, b);
    }

    public void printMaxResult(int max) {
        System.out.printf("\nMax between %s and %s is   %s", a, b, max).println();
    }
}
