package emrs;


public class FrontDeskHandler {
    public static void displayMenu(){

        displayMessage("""
                
                
                FRONT DESK
               ==============
               1 -> Register Patient
               2 -> Book Appointment
               3 -> Generate Medical Report
               4 -> Logout
               """);
        switchForDisplayMenu();
    }

    public static void switchForDisplayMenu(){
        String option = InputHandler.input("Enter your option: ");
        switch(option){
            case "1" -> registerPatient();
            case "2" -> bookAppointment();
            case "3" -> generateMedicalReport();
            case "4" -> logout();
            default -> {
                InputHandler.displayMessage("Enter a valid option\n");
                switchForDisplayMenu();
            }
        }
    }

    private static void generateMedicalReport() {
        String patientID = InputHandler.input("Enter Patient ID: ").trim();
        try {
            FrontDesk.generateMedicalRecord(patientID);
        }catch (Exception e){
            displayMessage("No medical record found");
        }
        displayMenu();
    }

    private static void bookAppointment() {
        try {

            String patientID = InputHandler.input("Enter patient ID: ");
            String specialization = InputHandler.input("Enter specialization(eg Dentist): ");
            String date = InputHandler.collectAndValidateDate("Enter date(DD-MM-YYYY): ");
            String time = InputHandler.collectAndValidateTime("Enter time(HH:mm:ss): 24hrs format ");

            Patient patient = Database.searchPatient(patientID);
            Appointment newAppointment = FrontDesk.bookAppointment(patient, date, time,specialization);
            patient.addAppointment(newAppointment);

        }catch (IllegalArgumentException e) {
            displayMessage(e.getMessage());
        }finally {
            displayMenu();
        }
    }

    private static void registerPatient() {
        try {
            String name = InputHandler.collectAndValidateName("Enter your name: ");
            String phone = InputHandler.collectAndValidatePhone("Enter your phone number: ");
            String address = InputHandler.input("Enter your address: ");
            String email = InputHandler.collectAndValidateEmail("Enter your email: ");
            String DOB = InputHandler.collectAndValidateDate("Enter your Date of birth(dd-mm-yyyy): ");
            Patient newPatient = FrontDesk.registerPatient(name, DOB, address, phone, email);
            displayMessage("Your ID is " + newPatient.getID());
        }catch (IllegalArgumentException err) {
            displayMessage(err.getMessage());
        }finally {
            displayMenu();
        }
    }

    public static void displayMessage(String prompt){
        System.out.print(prompt);
    }

    private static void logout(){
        FrontDesk loggedInOfficer = EMRS.getLoggedInFrontDesk();
        if(loggedInOfficer == null){
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
