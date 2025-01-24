package emrs;

public class PatientHandler {
    public static void displayMenu(){

       InputHandler.displayMessage("""
                   
                   
                   PATIENT MENU
                  ==============
                  1 -> Check Appointment
                  2 -> View Medical Details
                  3 -> Logout
               """);
        switchForDisplayMenu();
    }

    public static void switchForDisplayMenu() {
        String option = InputHandler.input("Enter your option: ");
        switch(option){
            case "1" -> checkAppointment();
            case "2" -> viewMedicalDetails();
            case "3" -> logout();
            default -> {
                        InputHandler.displayMessage("Please enter a valid option\n");
                        switchForDisplayMenu();
            }
        }

    }

    private static void viewMedicalDetails() {
        if(EMRS.getLoggedInPatient() == null) {
            InputHandler.displayMessage("No patient is logged in. Please log in first.");
            EMRS.displayMenu();
        }
        try {
            EMRS.getLoggedInPatient().viewMedicalHistory();
        }catch (IllegalArgumentException err){
            InputHandler.displayMessage(err.getMessage());
        }finally {
            displayMenu();
        }

    }

    private static void checkAppointment() {
        if(EMRS.getLoggedInPatient() == null) {
            InputHandler.displayMessage("No patient is logged in. Please log in first.");
            displayMenu();
        }
        try {
            EMRS.getLoggedInPatient().checkAppointment();
        }catch (IllegalArgumentException e){
            InputHandler.displayMessage(e.getMessage());
        }finally {
            displayMenu();
        }

    }
    private static void logout(){

        if(EMRS.getLoggedInPatient() == null){
            InputHandler.displayMessage("You are not logged in\n");
        }
        else{
            InputHandler.displayMessage("==============================================\n");
            InputHandler.displayMessage("Logout successful\n");
            InputHandler.displayMessage("==============================================\n");
        }
        EMRS.displayMenu();
    }
}




