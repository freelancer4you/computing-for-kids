package de.goldmann.apps.root.dto;

import java.io.Serializable;

public class UserDTO implements Serializable {

    private static final long serialVersionUID = -1973241612748624323L;

    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String password;
    private String phoneNumber;
    private Adress adress;
    private String registrationDate;


    public UserDTO() {
    }

    public UserDTO(final String firstName, final String lastName, final String userName, final String email,
            final String password, final String phoneNumber, final Adress adress, final String registration) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.adress = adress;
        this.phoneNumber = phoneNumber;
        this.registrationDate = registration;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Adress getAdress() {
        return adress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setAdress(final Adress adress) {
        this.adress = adress;
    }

    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

}
