package app.mirea.ru.rtuapp.models;

public class Contact {

    private String name;
    private String phoneNumber;

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }
}
