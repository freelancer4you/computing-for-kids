package de.goldmann.apps.root.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import de.goldmann.apps.root.dto.Adress;
import de.goldmann.apps.root.dto.UserDTO;

@Entity
@DiscriminatorValue("USER")
public class User extends UserId {

    private static final long serialVersionUID = 8137228294211060781L;

    private static final int  MAXLEN_NAME      = 81;
    // private static final int MAXLEN_USER_NAME = 12;

    @Column(name = "salutation", nullable = false)
    private String            salutation;

    @Column(name = "title", nullable = true)
    private String            title;

    @Column(name = "lastname", nullable = false, length = MAXLEN_NAME)
    private String lastName;

    @Column(name = "firstname", nullable = true, length = MAXLEN_NAME)
    private String firstName;

    @Column(name = "password", nullable = false)
    private String passwordDigest;

    @Column(name = "phonenumber", nullable = false)
    private String phoneNumber;

    @Embedded
    private PostAdress adresse;

    @Column(name = "childname", nullable = true)
    private String            childName;

    @Column(name = "childage", nullable = true)
    private String            childAge;

    User() {
        super();
        setRegistrationTyp(RegistrationTyp.DEFAULT);
    }

    public User(final UserDTO user) {
        this(user, UserRole.USER);
    }

    public User(final UserDTO user, final UserRole role) {
        super(user.getEmail(), role);
        setRegistrationTyp(RegistrationTyp.DEFAULT);
        salutation = user.getSalutation();
        title = user.getTitle();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        passwordDigest = new BCryptPasswordEncoder().encode(RandomStringUtils.random(10));
        phoneNumber = user.getPhoneNumber();
        final Adress adress = user.getAdress();
        adresse = new PostAdress(adress.getStreet(), adress.getZipcode(), adress.getCity(), adress.getHouseNr());
        childName = user.getChildName();
        childAge = user.getChildAge();
    }

    public String getSalutation() {
        return salutation;
    }

    public String getTitle() {
        return title;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPasswordDigest() {
        return passwordDigest;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public PostAdress getAdresse() {
        return adresse;
    }

    public String getChildName() {
        return childName;
    }

    public String getChildAge() {
        return childAge;
    }

    @Override
    public String toString() {
        return "User [" + (email != null ? "email=" + email + ", " : "")
                + (lastName != null ? "lastName=" + lastName + ", " : "")
                + (firstName != null ? "firstName=" + firstName + ", " : "")
                + (phoneNumber != null ? "phoneNumber=" + phoneNumber + ", " : "")
                + (adresse != null ? "adresse=" + adresse + ", " : "")
                + (registrationDate != null ? "registrationDate=" + registrationDate + ", " : "")
                + (childName != null ? "childName=" + childName + ", " : "")
                + (childAge != null ? "childAge=" + childAge : "") + "]";
    }

}
