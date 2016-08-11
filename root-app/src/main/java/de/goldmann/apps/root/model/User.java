package de.goldmann.apps.root.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import de.goldmann.apps.root.dto.Adress;
import de.goldmann.apps.root.dto.NewUserDTO;

@Entity
@Table(name = "USERS")
public class User implements Serializable {

    private static final long serialVersionUID = 8137228294211060781L;

    @Id
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "lastname", nullable = false)
    private String lastName;

    @Column(name = "firstname", nullable = true)
    private String firstName;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String passwordDigest;

    @Column(name = "phonenumber", nullable = false)
    private String phoneNumber;

    @Embedded
    private PostAdress adresse;

    public User() {
    }

    public User(final NewUserDTO user) {
        this.username = user.getUserName();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.passwordDigest = new BCryptPasswordEncoder().encode(user.getPassword());
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        final Adress adress = user.getAdress();
        this.adresse = new PostAdress(adress.getStreet(), adress.getPostcode(), adress.getCity());
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

    public String getUsername() {
        return username;
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
        return "User [email=" + email + ", lastName=" + lastName + ", firstName=" + firstName + ", username=" + username
                + ", passwordDigest=" + passwordDigest + ", phoneNumber=" + phoneNumber + ", adresse=" + adresse + "]";
    }

}
