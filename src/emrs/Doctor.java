package emrs;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;


public class Doctor extends User{
    private String specialization;
    private ArrayList<Patient> patients = new ArrayList<>();
    private ArrayList<Appointment> appointments = new ArrayList<>();
    private final String  ID;
    private int number = 10;
    private String password;

    public Doctor(String name, String dob, String address,String phoneNumber,String email, String password, String specialization) {
        super(name, dob, address, phoneNumber, email);
        this.specialization = specialization;
        this.ID = generateID();
        this.password = password;
    }
    public void addMedicalHistory(String patientID, String diagnosis, String tests, String drugs) {

        Patient patient = Database.searchPatient(patientID);

        MedicalHistory medicalHistory = patient.getMedicalHistory();

        if (medicalHistory == null) {
            medicalHistory = new MedicalHistory(patient.getName(), diagnosis, tests, drugs);
            patient.addMedicalHistory(medicalHistory);

        } else {

            medicalHistory.updateMedicalHistory(diagnosis, tests, drugs);
        }

        System.out.println("Medical record for patient " + patient.getName() + " has been updated.");
    }

    public boolean login(String ID, String password) {
        return this.ID.equalsIgnoreCase(ID.trim()) && this.password.equalsIgnoreCase(password.trim());
    }

    public void updateAppointment(String patientID, String date, String time){
        if(appointments.isEmpty()) throw new IllegalArgumentException("appointments is empty\n");

        Appointment appointmentToUpdate = null;

        for(Appointment existingAppointment : appointments){
           if(existingAppointment.getPatient().getID().equalsIgnoreCase(patientID.trim())){
               appointmentToUpdate = existingAppointment;
               break;
           }
        }

        if(appointmentToUpdate == null){
            throw new IllegalArgumentException("No appointment found for patient with ID: " + patientID + "\n");
        }
        try {
            appointmentToUpdate.updateAppointment(date, time);
        }catch (NumberFormatException err){
            System.out.println("Invalid date or time format. Please enter a valid timestamp.");
        }
    }


//    public void updateMedicalRecord(String ID,String diagnosis) {
//        MedicalHistory medicalHistoryToUpdate = null;
//
//        for (Patient patient : patients) {
//            if (patient.getID().equals(ID)) {
//                medicalHistoryToUpdate = patient.getMedicalHistory();
//                break;
//            }
//        }
//        if (medicalHistoryToUpdate == null) {
//            throw new IllegalArgumentException("No medical history found for patient with ID: " + ID);
//        }4
//
//        medicalHistoryToUpdate.updateMedicalHistory(diagnosis, "", "");
//
//    }

//        public void updateMedicalRecord(String ID,String diagnosis, String test, String drug){
//            if (patients.isEmpty()) {
//                throw new IllegalArgumentException("No patients found in the system.");
//            }
//
//            MedicalHistory medicalHistoryToUpdate = null;
//
//        for(Patient patient : patients){
//            if(patient.getID().equalsIgnoreCase(ID.trim())){
//                medicalHistoryToUpdate = patient.getMedicalHistory();
//                break;
//            }
//        }
//        if(medicalHistoryToUpdate == null){
//            throw new IllegalArgumentException("No medical history found for patient with ID: " + ID + "\n");
//        }
//
//        medicalHistoryToUpdate.updateMedicalHistory(diagnosis,test,drug);
//    }

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
        System.out.printf("Dr. %s has a new appointment on %s at %s%n", getName(), appointment.getDate(), appointment.getTime());

    }
    private String generateID() {
        number++;
        return "DRN" + String.valueOf(number);
    }

    public void generateMedicalRecord(String ID){
        try {
            Patient patient = Database.searchPatient(ID);
            MedicalHistory medicalHistory = patient.getMedicalHistory();
            System.out.println("=== Medical Record ===");
            System.out.println("Name: " + patient.getName());
            System.out.println("Date of Birth: " + patient.getDob());
            System.out.println("Medical History: " + medicalHistory.getDetails());
            System.out.println("======================");
        } catch (Exception err){
            System.out.println(err.getMessage());
        }
    }

    public boolean isAvailable(String date, String time) {
        for (Appointment appointment : appointments) {
            if (appointment.getDate().equalsIgnoreCase(date.trim()) && appointment.getTime().equalsIgnoreCase(time.trim())) {
                return false;
            }
        }
        return true;
    }
    public ArrayList<Patient> getPatients() {
        return patients;
    }
    public String getID() {
        return ID;
    }

    public  Patient findPatientByID(String ID){

        if(patients.isEmpty())throw new IllegalArgumentException("There are no patients in the database.\n");
        for(Patient patient : patients){
            if(patient.getID().equalsIgnoreCase(ID))return patient;
        }
        throw new IllegalArgumentException("Patient not found");
    }
    public String getSpecialization() {
        return specialization;
    }

    public void viewAppointment() {
        if(appointments.isEmpty()) throw new IllegalArgumentException("There is no appointment available\n");
        for (Appointment appointment : appointments) {
            appointment.displayAppointment();
        }

    }
}
