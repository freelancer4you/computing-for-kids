package de.goldmann.apps.root.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import de.goldmann.apps.root.dto.Adress;
import de.goldmann.apps.root.dto.UserDTO;

@Entity
@Table(name = "USERS")
public class User implements Serializable {

    private static final long serialVersionUID = 8137228294211060781L;

    private static final int  MAXLEN_NAME      = 81;
    private static final int  MAXLEN_USER_NAME = 12;

    @Id
    @Column(name = "email", nullable = false, unique = true)
    private String email;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private UserRole          role;

    @Embedded
    private PostAdress adresse;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "registration", nullable = false)
    private Date registrationDate;

    @Column(name = "childname", nullable = true)
    private String            childName;

    @Column(name = "childage", nullable = true)
    private String            childAge;

    public User() {
    }

    public User(final UserDTO user) {
        this(user, UserRole.USER);
    }

    public User(final UserDTO user, final UserRole role) {
        salutation = user.getSalutation();
        title = user.getTitle();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        passwordDigest = new BCryptPasswordEncoder().encode(RandomStringUtils.random(10));
        email = user.getEmail();
        phoneNumber = user.getPhoneNumber();
        final Adress adress = user.getAdress();
        adresse = new PostAdress(adress.getStreet(), adress.getZipcode(), adress.getCity(), adress.getHouseNr());
        this.role = role;
        final LocalDateTime ldt = LocalDateTime.now();
        final ZonedDateTime zdt = ldt.atZone(ZoneId.systemDefault());
        registrationDate = Date.from(zdt.toInstant());
        childName = user.getChildName();
        childAge = user.getChildAge();
    }

    public String getSalutation() {
        return salutation;
    }

    public String getTitle() {
        return title;
    }

    public String getEmail() {
        return email;
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

    public UserRole getRole() {
        return role;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public String getChildName() {
        return childName;
    }

    public String getChildAge() {
        return childAge;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (email == null ? 0 : email.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (email == null) {
            if (other.email != null) {
                return false;
            }
        }
        else if (!email.equals(other.email)) {
            return false;
        }
        return true;
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
