package emrs;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class InputHandler {

    private static final Scanner receiver = new Scanner(System.in);

    public static String collectAndValidateName(String prompt){

        String namePattern = "^[A-Za-z]+(?: [A-Za-z]+)*$";

        while(true){
            displayMessage(prompt);
            String userInput = receiver.nextLine().trim();
            if(userInput.matches(namePattern))  return userInput.toLowerCase();
            else displayMessage("Enter a valid name\n\n");
        }
    }

    public static String collectAndValidatePassword(String prompt){
        String passwordPattern = "^(?=.*[a-zA-Z])(?=.*\\d).{4,}$";

        while(true){
            displayMessage(prompt);
            String userInput = receiver.nextLine().trim();
            if(userInput.matches(passwordPattern))  return userInput.toLowerCase();
            else displayMessage(String.format("%s%n%n", "Password must be at least 4 characters long and include a number."));

        }

    }

    public static String collectAndValidateDate(String prompt) {
        String datePattern = "^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-(\\d{4})$";

        while (true) {
            displayMessage(prompt);
            String userInput = receiver.nextLine().trim();
            if (userInput.matches(datePattern)) return userInput.toLowerCase();
            else displayMessage(String.format("%s%n%n", "Enter a valid date"));

        }
    }
        public static String collectAndValidateTime(String prompt) {
            String timePattern = "^(?:[01]\\d|2[0-3]):[0-5]\\d:[0-5]\\d$";

            while (true) {
                displayMessage(prompt);
                String userInput = receiver.nextLine().trim();
                if (userInput.matches(timePattern)) return userInput.toLowerCase();
                else displayMessage(String.format("%s%n%n", "Enter a valid time"));

            }
        }

        public static String collectAndValidateEmail(String prompt){
             String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,10}$";

                while(true){
                    displayMessage(prompt);
                    String userInput = receiver.nextLine().trim();
                    if(userInput.matches(emailPattern))  return userInput.toLowerCase();
                    else displayMessage(String.format("%s%n%n", "Enter a valid email"));

             }

         }

         public static String collectAndValidatePhone(String prompt){
             String phoneNumberPattern = "^\\d{11}$";

             while(true){
                 String userInput = input(prompt).trim();
                 if(userInput.matches(phoneNumberPattern))  return userInput;
                 else displayMessage("Enter 11 digits only\n");
             }
         }

    public static String input(String prompt){
        Scanner receiver = new Scanner(System.in);

        while (true) {
            displayMessage(prompt);
            String userInput = receiver.nextLine().trim();
            if(!userInput.isEmpty()) return userInput.toLowerCase();
            else displayMessage(String.format("%n%s%n","This field cannot be empty"));
        }

    }

    public static char userChoiceInput(String prompt){

        while(true){
            displayMessage(prompt);
            String userInput = receiver.nextLine().trim().toUpperCase();
            if(userInput.isEmpty()) displayMessage("Field cannot be empty");
            else if(!(userInput.charAt(0) == 'N' || userInput.charAt(0) == 'Y')) displayMessage("Enter a valid option");
            else return userInput.charAt(0);
        }

    }

    private static void validatePassword(String password){
        String passwordPattern = "^(?=.*[a-zA-Z])(?=.*\\d).{4,}$";
        if(!password.matches(passwordPattern)) throw new IllegalArgumentException("Password must be at least 4 characters long and include a number");

    }



    public static void displayMessage(String prompt){
        System.out.print(prompt);
    }

}
