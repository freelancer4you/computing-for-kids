package de.goldmann.apps.root.dto;

import java.io.Serializable;

public class DefaultAccountDTO implements Serializable {

    private static final long serialVersionUID = -1973241612748624323L;

    private String            salutation;
    private String            title;
    private String            firstName;
    private String            lastName;
    private String            email;
    private String            password;
    private String            phoneNumber;
    private Adress            adress;
    private String            registrationDate;
    private String            childName;
    private String            childAge;

    public DefaultAccountDTO() {}

    public DefaultAccountDTO(final String salutation, final String title, final String firstName, final String lastName,
            final String email,
            final String password, final String phoneNumber, final Adress adress, final String registration,
            final String childName, final String childAge) {
        this.salutation = salutation;
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.adress = adress;
        this.phoneNumber = phoneNumber;
        registrationDate = registration;
        this.childName = childName;
        this.childAge = childAge;

    }

    public String getSalutation() {
        return salutation;
    }

    public String getTitle() {
        return title;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
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

    public String getChildName() {
        return childName;
    }

    public String getChildAge() {
        return childAge;
    }

    @Override
    public String toString() {
        return "UserDTO ["
                + (salutation != null ? "salutation=" + salutation + ", " : "")
                + (title != null ? "title=" + title + ", " : "")
                + (firstName != null ? "firstName=" + firstName + ", " : "")
                + (lastName != null ? "lastName=" + lastName + ", " : "")
                + (email != null ? "email=" + email + ", " : "")
                + (password != null ? "password=" + password + ", " : "")
                + (phoneNumber != null ? "phoneNumber=" + phoneNumber + ", " : "")
                + (adress != null ? "adress=" + adress + ", " : "")
                + (registrationDate != null ? "registrationDate=" + registrationDate + ", " : "")
                + (childName != null ? "childName=" + childName + ", " : "")
                + (childAge != null ? "childAge=" + childAge : "")
                + "]";
    }

}
