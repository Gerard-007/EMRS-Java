package emrs;

import java.io.IOException;
import java.util.Date;


public class DoctorHandler {

    public static void displayMenu(){

       InputHandler.displayMessage("""
                DOCTOR MENU
               ==============
               1 -> Add/Update Medical Information
               2 -> Update Appointment
               3 -> Generate Medical Report
               4 -> View Appointments
               5 -> Logout
               """);
        switchForDisplayMenu();
    }

    public static void switchForDisplayMenu(){
        String option = InputHandler.input("Enter your option: ");
        switch(option){
            case "1" -> addMedicalRecord();
            case "2" -> updateAppointment();
            case "3" -> generateMedicalReport();
            case "4" -> viewAppointment();
            case "5" -> logout();
            default -> {
                InputHandler.displayMessage("Invalid option\n");
                switchForDisplayMenu();
            }
        }
    }

    private static void viewAppointment() {
        if(EMRS.getLoggedInDoctor() == null){
            InputHandler.displayMessage("No doctor is logged in. Please log in first.");
            EMRS.displayMenu();
        }
        try {
            EMRS.getLoggedInDoctor().viewAppointment();
        }catch (IllegalArgumentException e){
            InputHandler.displayMessage("Error while viewing appointment");
            displayMenu();
        }finally {
            displayMenu();
        }
    }

    private static void addMedicalRecord() {
            if(EMRS.getLoggedInDoctor() == null){
                InputHandler.displayMessage("No doctor is logged in. Please log in first.");
                EMRS.displayMenu();
            }
            String patientID = InputHandler.input("Enter patient ID: ");
            String diagnosis = InputHandler.input("Enter diagnosis: ");
            String test = InputHandler.input("Enter test: ");
            String drug = InputHandler.input("Enter drug: ");

            try {
                EMRS.getLoggedInDoctor().addMedicalHistory(patientID, diagnosis, test, drug);
            }catch (IllegalArgumentException e){
                InputHandler.displayMessage(e.getMessage());
                addMedicalRecord();
            }finally {
                displayMenu();
            }

    }

    private static void generateMedicalReport() {
        if(EMRS.getLoggedInDoctor() == null){
            InputHandler.displayMessage("No doctor is logged in. Please log in first.");
            return;
        }

        String id = InputHandler.input("Enter ID : ");
        try {
            EMRS.getLoggedInDoctor().generateMedicalRecord(id);
        }catch (IllegalArgumentException e){
            InputHandler.displayMessage(e.getMessage());
            displayMenu();
        }finally {
            displayMenu();
        }
    }

//    private static void updateMedicalRecord() {
//        if(EMRS.getLoggedInDoctor() == null){
//            InputHandler.displayMessage("No doctor is logged in. Please log in first.");
//            return;
//        }
//        String id = InputHandler.input("Enter patient ID : ");
//        String diagnosis = InputHandler.input("Enter Diagnosis : ");
//        String test = InputHandler.input("Enter Test : ");
//        String drug = InputHandler.input("Enter drug : ");
//
//        try {
//            EMRS.getLoggedInDoctor().updateMedicalRecord(id, diagnosis, test, drug);
//        }catch (Exception e){
//            InputHandler.displayMessage(e.getMessage());
//            displayMenu();
//        }finally {
//            displayMenu();
//        }
//    }

    private static void updateAppointment() {
        if(EMRS.getLoggedInDoctor() == null){
            InputHandler.displayMessage("No doctor is logged in. Please log in first.");
            return;
        }
        String patientID = InputHandler.input("\nEnter patient ID: ");
        String date = InputHandler.collectAndValidateDate("Enter date(DD-MM-YYYY): ");
        String time = InputHandler.collectAndValidateTime("\nEnter time(HH:mm:ss): 24hrs format: ");


        try {
            EMRS.getLoggedInDoctor().updateAppointment(patientID, date, time);
            InputHandler.displayMessage("Appointment updated successfully.\n");
        } catch (Exception e) {
            InputHandler.displayMessage( e.getMessage());
            displayMenu();
        }finally {
            displayMenu();
        }
    }
    private static void logout(){

        if(EMRS.getLoggedInDoctor() == null){
            InputHandler.displayMessage("You are not logged in");
        }
        else{
            InputHandler.displayMessage("==============================================\n");
            InputHandler.displayMessage("Logout successful\n");
            InputHandler.displayMessage("==============================================\n\n");
        }
        EMRS.displayMenu();
    }
}
