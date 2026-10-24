package Packt.LearnAutomationTestingwithJavaandSeleniumWebdriver.ArrayAndArrayList;

public class MostStringHasCharsRunner {

    public static void main(String[] args) {
        String[] daysOfWeek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

        System.out.println("Most day with Characters : " + mostDayWithCharacters(daysOfWeek));


        // parcourir le table daysOfWeek
        for (int i = daysOfWeek.length - 1; i >= 0; i--) {
            System.out.print(" " + daysOfWeek[i]);
        }

    }

    private static String mostDayWithCharacters(String[] daysOfWeek) {
        String dayWithMostCharacters = "";

        for (String day : daysOfWeek) {
            if (day.length() > dayWithMostCharacters.length()) {
                dayWithMostCharacters = day;
            }
        }
        return dayWithMostCharacters;
    }
}
