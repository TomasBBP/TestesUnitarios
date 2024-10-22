package Classes;

import java.util.Date;

public class Contact {
    private String firstName,lastName, phone, email;
    private Date birthday;

    public Contact(String firstName, String phone){
        this.firstName = firstName;
        this.phone = phone;
    }
    public Contact(String firstName, String lastName, String phone){
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }
    public Contact(String firstName, String lastName, String phone, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }
    public Contact(String firstName, String lastName, String phone, String email, Date birthday){
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.birthday = birthday;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString(){
        String result = "Nome: "+ this.getFirstName() +
                "\tUltimo Nome: "+ this.getLastName() +
                "\nPhone: "+this.getPhone() + "\tEmail: "
                +this.getEmail();
                if(this.birthday != null)
                    result += "\nBirthday: " + this.birthday.toString();
        return result;
    }
    public boolean matches(String term) {
        if(firstName.compareTo(term)==0) return true;
        if(lastName.compareTo(term)==0) return true;
        if(phone.compareTo(term)==0) return true;
        if(email.compareTo(term)==0) return true;
        if(birthday != null && birthday.toString().compareTo(term)==0) return true;
        return false;
    }
}
