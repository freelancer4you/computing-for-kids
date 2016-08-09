package de.goldmann.apps.root.dto;

public class NewUserDTO
{
    private String  firstName;
    private String  lastName;
    private String  userName;
    private String  email;
    private String  password;
    private boolean featureUpdate;

    public NewUserDTO()
    {}

    public NewUserDTO(String firstName, String lastName, String userName, String email, String password,
            boolean featureUpdate)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.featureUpdate = featureUpdate;
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

    public String getPassword()
    {
        return password;
    }

    public String getUserName()
    {
        return userName;
    }

    public boolean isFeatureUpdate()
    {
        return featureUpdate;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setFeatureUpdate(boolean featureUpdate)
    {
        this.featureUpdate = featureUpdate;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    @Override
    public String toString()
    {
        return "NewUserDTO [firstName=" + firstName + ", lastName=" + lastName + ", userName=" + userName
                + ", email=" + email + ", password=" + password + ", featureUpdate=" + featureUpdate + "]";
    }

}
