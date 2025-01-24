package emrs;

import javax.print.DocFlavor;

public class EMRS {
//    private static final SuperAdmin superAdmin = new SuperAdmin("ARM11", "1234");
    private static Patient loggedInPatient;
    private static Doctor loggedInDoctor;
    private static FrontDesk loggedInFrontDesk;

    public static void main(String[] args) {
        displayMenu();
    }

    public static void displayMenu() {
        displayMessage("""
                EMRS MAINMENU
               ================
               1 -> Front Desk Login
               2 -> Patient Login
               3 -> Doctor Login
               4 -> Change Password
               5 -> Exit
               """);
        switchForDisplayMenu();

    }
    public static void switchForDisplayMenu() {
        String option = InputHandler.input("Enter your option: ");
        switch (option) {
            case "1" -> frontDeskLogin();
            case "2" -> patientLogin();
            case "3" -> doctorLogin();
            case "4" -> changePassword();
            default -> {
                displayMessage("Invalid option");
               switchForDisplayMenu();
            }
        }
    }

    private static void changePassword() {
        String id = InputHandler.input("Enter ID generate at the reception upon registration: ");
        String oldPassword = InputHandler.input("Enter default password given at reception upon registration: ");
        String newPassword = InputHandler.collectAndValidatePassword("Enter new password: ");
        try {
            Patient patient = Database.searchPatient(id);
            patient.changePassword(oldPassword, newPassword);
            InputHandler.displayMessage("Password update successfully\n");
        }catch (IllegalArgumentException err){
            InputHandler.displayMessage("Enter the ID given to you after registration\n");
        }finally {
            displayMenu();
        }

    }

    private static void doctorLogin() {
        String id = InputHandler.input("Enter ID: ");
        String password = InputHandler.input("Enter Password: ");



        try {
            Doctor doctor = Database.searchDoctor(id);
            boolean isSuccessfully = doctor.login(id, password);
            if (!isSuccessfully) {
                displayMessage("Invalid ID or password\n\n");
                displayMenu();
            }
            else{
                loggedInDoctor = doctor;
                displayMessage("==============================================\n");
                displayMessage(String.format("Welcome back %s%n", doctor.getName().toUpperCase()));
                displayMessage("==============================================\n");
                DoctorHandler.displayMenu();
            }

        }catch (IllegalArgumentException err){
            displayMessage(err.getMessage());
            displayMenu();
        }catch (Exception e){
            displayMessage(e.getMessage());

        }

    }

    private static void patientLogin() {
        String id = InputHandler.input("Enter ID: ");
        String password = InputHandler.input("Enter Password: ");



        try {
            Patient patient= Database.searchPatient(id);
            boolean isSuccessful = patient.login(id, password);
            if (!isSuccessful) {
                displayMessage("Invalid ID or password\n\n");
                displayMenu();

            }
            else{
                loggedInPatient = patient;
                displayMessage("==============================================\n");
                displayMessage(String.format("Welcome back %s%n", patient.getName().toUpperCase()));
                displayMessage("==============================================\n");
                PatientHandler.displayMenu();
            }

        }catch (IllegalArgumentException err){
            displayMessage(err.getMessage());
            displayMenu();
        }catch (Exception e){
            displayMessage(e.getMessage());

        }

    }

    private static void frontDeskLogin() {
        String id = InputHandler.input("Enter ID: ");
        String password = InputHandler.input("Enter Password: ");

        try {
            FrontDesk frontDesk = Database.searchFrontDesk(id);
            boolean isSuccessfully = frontDesk.login(id, password);
            if (!isSuccessfully) {
                displayMessage("Invalid ID or password\n\n");
                displayMenu();

            }
            else{
                loggedInFrontDesk = frontDesk;
                displayMessage("==============================================\n");
                displayMessage(String.format("Welcome back %s%n", frontDesk.getName().toUpperCase()));
                displayMessage("==============================================");
                FrontDeskHandler.displayMenu();
            }

        }catch (IllegalArgumentException err){
            displayMessage(err.getMessage());
            displayMenu();
        }catch (Exception e){
            displayMessage(e.getMessage());

        }
    }

    public static void displayMessage(String prompt){
        System.out.print(prompt);
    }


    public static Doctor getLoggedInDoctor() {
        return loggedInDoctor;
    }

    public static Patient getLoggedInPatient() {
        return loggedInPatient;
    }

//    public static SuperAdmin getSuperAdmin() {
//        return superAdmin;
//    }

    public static FrontDesk getLoggedInFrontDesk() {
        return loggedInFrontDesk;
    }
}
