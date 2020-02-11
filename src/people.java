import java.util.ArrayList;
import java.util.List;

public class people {
    private String name;
    private String phoneNumber;
//    private String email;

    public people(String name, String phoneNumber){
        this.name = name;
        this.phoneNumber = phoneNumber;
//        this.email = email;
    }
    
    //getters//
    public String getName(){
        return this.name;
    }
    public String getPhoneNumber(){
        return this.phoneNumber;
    }

    //    public String getEmail(){
//        return this.email;
//    }

    //Setters//
    public void setName(java.lang.String name) {
        this.name = name;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
//    public void setEmail(String email){
//        this.email = email;
//    }

    // Creating string from instance of contact
    public String contactString(){
        return this.name + " | " + this.phoneNumber;
    }


}
