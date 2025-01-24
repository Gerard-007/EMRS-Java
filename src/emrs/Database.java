package emrs;

import java.util.ArrayList;

public class Database {
    private  static ArrayList<Doctor> doctors = new ArrayList<>();
    private  static ArrayList<Patient> patients = new ArrayList<>();
    public  static ArrayList<MedicalHistory> medicalHistories = new ArrayList<>();
    public static ArrayList<FrontDesk> frontDesks = new ArrayList<>();
    private static ArrayList<Appointment> appointments = new ArrayList<>();

    static {
        addDefaultDoctorsANDFrontDesk();
    }
    public static ArrayList<Appointment> getAppointments() {
        return appointments;
    }




    public static ArrayList<FrontDesk> getFrontDesks() {
        return frontDesks;
    }



    public static ArrayList<Doctor> getDoctors() {
        return doctors;
    }

    public static ArrayList<Patient> getPatients() {
        return patients;
    }

    public static ArrayList<MedicalHistory> getMedicalHistories() {
        return medicalHistories;
    }

    public static Patient searchPatient(String ID){
        if(patients.isEmpty())throw new IllegalArgumentException("There are no patients in the database.\n");
        for(Patient patient : patients){
            if(patient.getID().equalsIgnoreCase(ID))return patient;
        }
        throw new IllegalArgumentException("Patient not found");
    }

    public static FrontDesk searchFrontDesk(String ID){
        if(frontDesks.isEmpty())throw new IllegalArgumentException("There are no officers in the database.\n");
        for(FrontDesk frontDesk : frontDesks){
            if(frontDesk.getID().equalsIgnoreCase(ID))return frontDesk;
        }
        throw new IllegalArgumentException("Officer with ID " + ID +  " not found");
    }

    public static Doctor searchDoctor(String ID){
        if(doctors.isEmpty())throw new IllegalArgumentException("There are no doctors in the database.");
        for(Doctor doctor : doctors){
            if(doctor.getID().equalsIgnoreCase(ID)) return doctor;
        }
        throw new IllegalArgumentException("doctor not found");
    }
    public static void addDefaultDoctorsANDFrontDesk() {
        doctors.add(new Doctor("John Doe", "01-01-1980", "123 Main St", "1234567890", "johndoe@email.com", "2222", "Cardiology"));
        doctors.add(new Doctor("Jane Smith", "05-12-1975", "456 Oak St", "0987654321", "janesmith@email.com", "2222", "Neurology"));
        doctors.add(new Doctor("Emily Brown", "20-07-1982", "789 Pine St", "1122334455", "emilybrown@email.com", "2222", "Orthopedics"));
        doctors.add(new Doctor("Emily Tom", "21-07-1982", "799 Pine St", "1122334456", "emilytom@email.com", "2222", "Dentist"));

        frontDesks.add(new FrontDesk("admin", "admin123"));
        System.out.println("Default doctors and front desk officer added to the database.");
    }

}

