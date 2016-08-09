package de.goldmann.apps.root.model;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import de.goldmann.apps.root.dto.NewUserDTO;

@Entity
@Table(name = "USERS")
@NamedQueries(
{ @NamedQuery(name = User.FIND_BY_USERNAME, query = "select u from User u where username = :username") })
public class User extends AbstractEntity
{

    public static final String FIND_BY_USERNAME = "user.findByUserName";

    private String             lastName;
    private String             firstName;
    private String             username;
    private String             passwordDigest;
    private String             email;

    public User()
    {}

    public User(NewUserDTO user)
    {
        this.username = user.getUserName();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        // TODO this does not work, but should work
        this.passwordDigest = new BCryptPasswordEncoder().encode(user.getPassword());
        // this.passwordDigest = user.getPassword();
        this.email = user.getEmail();
    }

    public String getEmail()
    {
        return email;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getPasswordDigest()
    {
        return passwordDigest;
    }

    public String getUsername()
    {
        return username;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public void setPasswordDigest(String passwordDigest)
    {
        this.passwordDigest = passwordDigest;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    @Override
    public String toString()
    {
        return "User [lastName=" + lastName + ", firstName=" + firstName + ", userName=" + username
                + ", passwordDigest=" + passwordDigest + ", email=" + email + "]";
    }

}
