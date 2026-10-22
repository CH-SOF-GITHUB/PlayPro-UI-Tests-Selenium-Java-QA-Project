package Packt.LearnAutomationTestingwithJavaandSeleniumWebdriver.Loops;

public class LoopsRunner {

    public static void main(String[] args) {
        int x = 7;

        for (int i = 3; i <= 10; i = i + 1) {
            if (i % 2 != 0)
                continue;
            System.out.print(i + " \n");
        }

        for (int j = 0; j <= 10; j++) {
            if (j == 5) {
                continue;
            }
            System.out.print(j + " ");
        }

        String someString = "This is a lot of text again";
        System.out.println("\n\n" + someString.indexOf("lot"));
        System.out.println(someString.charAt(10));
        System.out.println(someString.lastIndexOf('i'));
    }
}
