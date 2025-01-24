package emrs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class User {
    private String name;
    private String dob;
    private ContactInfo contactInfo;

    public User(){}

    public User(String name, String dob, String address,String phoneNumber,String email) {
        validateInfo(name);
        this.name = name;
        this.dob = dob;
        this.contactInfo = new ContactInfo(address,phoneNumber,email);
    }

    public String getName() {
        return name;
    }

    public String getDob() {
        return dob;
    }

    private static void validateInfo(String name){
        String namePattern = "^[A-Za-z'-]+(?: [A-Za-z'-]+)*$";
        if (!name.matches(namePattern)) throw new IllegalArgumentException("Enter a valid name");
        if(name.isEmpty()) throw new NullPointerException("name cannot be empty");
    }



}

