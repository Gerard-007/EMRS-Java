package emrs;

import java.security.PrivateKey;
import java.sql.Time;
import java.util.Date;

public class Appointment {
    private Patient patient;
    private final String ID;
    private String date;
    private String time;
    private Doctor doctor;
    private static int number = 10;

    public Appointment(Patient patient,Doctor doctor, String date, String time) {
        this.patient = patient;
        this.date = date;
        this.time = time;
        this.ID = generateID();
        this.doctor = doctor;
        System.out.println("APPOINTMENT CREATED  " + this.ID);
    }

    private String generateID() {
        number++;
        return "ARN" + String.valueOf(number);
    }

    public void updateAppointment(String date, String time) {

        if(this.date.equals(date) && this.time.equals(time)) {
            System.out.printf("Conflict detected: Doctor %s already has an appointment at this time.", patient.getName());
        }
        this.date = date;
        this.time = time;
        System.out.println("Appointment updated");

    }

    public void cancelAppointment(){
        this.date = null;
        this.time = null;
        System.out.println("Appointment cancelled");
    }

    public Patient getPatient() {
        return patient;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public void displayAppointment(){

        System.out.println("Name: " + this.patient.getName());
        System.out.println("Date: " + this.date);
        System.out.println("Time: " + this.time);
        System.out.println("Doctor: " + this.doctor.getName());
        System.out.println("Specialization: " + this.doctor.getSpecialization() + "\n\n");

    }

}
