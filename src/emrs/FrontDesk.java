package emrs;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

public class FrontDesk{
    private String name;
    private String password;
    private String ID;
    private static int number = 10;


    public FrontDesk(String name,  String password) {
        validateInfo(name, password);
        this.name = name;
        this.password = password;
        this.ID = generateID();
    }

    public static  Patient registerPatient(String name, String dob, String address,String phoneNumber,String email){
        Patient patient = new Patient(name, dob, address, phoneNumber, email);
        Database.getPatients().add(patient);
        System.out.printf("%nPatient %s successfully registered.%n", name);
        return patient;
    }
    public static Appointment bookAppointment(Patient patient, String date, String time, String specialization) {

        Doctor availableDoctor = findAvailableDoctor(specialization, date, time);
        if (availableDoctor == null) {
            throw new IllegalArgumentException("No available doctor with specialization: " + specialization + ". Please choose another time.");

        }
        ArrayList<Appointment> appointments = Database.getAppointments();
        for(Appointment existingAppointment : appointments){
            if (existingAppointment.getDate().trim().equals(date.trim()) &&
                    existingAppointment.getTime().trim().equals(time.trim())) {
                System.out.println("Appointment slot already taken! Choose another time.");
                throw new IllegalArgumentException("Appointment slot already taken! Choose another time.");
            }

        }
        Appointment newAppointment = new Appointment(patient, availableDoctor, date,time);
        appointments.add(newAppointment);
        availableDoctor.addAppointment(newAppointment);
        availableDoctor.getPatients().add(patient);
        System.out.printf("Appointment for %s successfully booked.%n", patient.getName());
        return newAppointment;
    }

    public static Patient searchPatient(String ID){
        ArrayList<Patient> patients = Database.getPatients();
        if(patients.isEmpty())throw new IllegalArgumentException("There are no patients in the database.");
        for(Patient patient : patients){
            if(patient.getID().equalsIgnoreCase(ID))return patient;
        }
        throw new IllegalArgumentException("Patient not found");
    }

    public boolean login(String ID, String password) {
        return this.ID.equalsIgnoreCase(ID) && this.password.equalsIgnoreCase(password);
    }

    public static Doctor searchDoctor(String ID){
        ArrayList<Doctor> doctors = Database.getDoctors();
        if(doctors.isEmpty())throw new IllegalArgumentException("There are no doctors in the database.");
        for(Doctor doctor : doctors){
            if(doctor.getID().equalsIgnoreCase(ID)) return doctor;
        }
        throw new IllegalArgumentException("doctor not found");
    }

    public static void generateMedicalRecord(String ID){
        try {
            Patient patient = searchPatient(ID);
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
    private static String generateID() {
        number++;
        return "FRN" + String.valueOf(number);
    }
    private static void validateInfo(String name,String password){
        String namePattern = "^[A-Za-z'-]+(?: [A-Za-z'-]+)*$";
        if (!name.matches(namePattern)) throw new IllegalArgumentException("Enter a valid name");
        if(name.isEmpty()) throw new NullPointerException("name cannot be empty");
        validatePassword(password);
    }


    private static void validatePassword(String password){
        String passwordPattern = "^(?=.*[a-zA-Z])(?=.*\\d).{4,}$";
        if(!password.matches(passwordPattern)) throw new IllegalArgumentException("Password must be at least 4 characters long and include a number");

    }

    private static Doctor findAvailableDoctor(String specialization, String date, String time) {
        ArrayList<Doctor> doctors = Database.getDoctors();
        for (Doctor doctor : doctors) {
            if (doctor.getSpecialization().equalsIgnoreCase(specialization) && doctor.isAvailable(date, time)) {
                return doctor;
            }
        }
        return null;
    }
    public String getName() {
        return name;
    }

    public String getID() {
        return this.ID;
    }
}
