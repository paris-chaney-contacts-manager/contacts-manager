import java.util.ArrayList;
import java.util.List;

public class contacts {
    private List<String> contacts;
    private String name;
    private String phoneNumber;
    private String email;

    public contacts(String name, String phoneNumber,String email){
        this.contacts = new ArrayList<>();
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
    //getters//
    public String getName(){
        return this.name;
    }
    public String getPhoneNumber(){
        return this.phoneNumber;
    }
    public String getEmail(){
        return this.email;
    }

    //Setters//
    public void setName(java.lang.String name) {
        this.name = name;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    public void setEmail(String email){
        this.email = email;
    }

}
