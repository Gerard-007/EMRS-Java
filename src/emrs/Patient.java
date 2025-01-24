package emrs;

import java.util.ArrayList;
import java.util.Date;

public class Patient extends  User{

    private MedicalHistory medicalHistory;
    private ArrayList<Doctor> assignedDoctors = new ArrayList<>();
    private ArrayList<Appointment> appointments = new ArrayList<>();
    private final String  ID;
    private static int number = 10;
    private String password;

    public Patient(String name, String dob, String address, String phoneNumber, String email) {
        super(name,dob,address,phoneNumber,email);
        this.ID = generateID();
        this.password = "1111";
        System.out.printf("Tentative pin: %s. Kindly change password as soon as possible", password);
    }

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    public boolean login(String ID, String password) {
        return this.ID.equalsIgnoreCase(ID.trim()) && this.password.equalsIgnoreCase(password.trim());
    }
    public void viewMedicalHistory() {
        if(medicalHistory == null) throw new NullPointerException("No medical History found");
        System.out.println(this.medicalHistory.getDetails());
    }

    public void checkAppointment() {
        if(appointments.isEmpty()) throw new IllegalArgumentException("No appointments available");
        for(Appointment appointment : appointments) {
             appointment.displayAppointment();
        }
    }

    private String generateID() {
        number++;
        return "PRN" + String.valueOf(number);
    }
    public String getID() {
        return ID;
    }

    public MedicalHistory getMedicalHistory() {
        return medicalHistory;
    }
    public void addMedicalHistory(MedicalHistory medicalHistory) {
        this.medicalHistory = medicalHistory;
    }
    public String getName(){
        return super.getName();
    }

    public String getDOB(){
        return super.getDob().toString();
    }

    public void changePassword(String oldPassword, String newPassword){
        boolean isCorrect = this.password.equals(oldPassword);
        if(isCorrect){
            this.password = newPassword;
        }else throw new IllegalArgumentException("Old password Incorrect");

    }


}




