package Packt.LearnAutomationTestingwithJavaandSeleniumWebdriver.Conditions;

public class SwitchExercisesRunner {

    public static void main(String[] args) {
        String day = "Saturday";
        int theDay = determinateDay(day);
        System.out.println("Today is : " + theDay);

        //
        boolean isWeekly = isWeekDay(theDay);
        System.out.println("Is Week Day ? : " + isWeekly);
    }

    private static int determinateDay(String day) {
        switch (day) {
            case "Sunday":
                return 0;
            case "Monday":
                return 1;
            case "Tuesday":
                return 2;
            case "Wednesday":
                return 3;
            case "Thursday":
                return 4;
            case "Friday":
                return 5;
            case "Saturday":
                return 6;
            default:
                return -1;
        }
    }

    private static boolean isWeekDay(int dayNumber) {
        switch (dayNumber) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                return true;
        }
        return false;
    }
}
