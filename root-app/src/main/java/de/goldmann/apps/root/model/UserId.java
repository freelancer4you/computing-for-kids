package de.goldmann.apps.root.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "registrationtyp")
public abstract class UserId implements Serializable {

    private static final long serialVersionUID = -7954540961709019241L;

    protected static final int MAXLEN_NAME = 81;

    @Id
    @Column(name = "email", nullable = false, unique = true)
    private String            email;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "registration", nullable = false)
    private Date              registrationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private UserRole          role;

    @Column(name = "registrationtyp", nullable = false, length = 15)
    @Enumerated(EnumType.STRING)
    private RegistrationTyp   registrationTyp;

    UserId() {}

    public UserId(final String email, final UserRole role, final RegistrationTyp registrationTyp) {
        this.email = email;
        this.role = role;
        this.registrationTyp = registrationTyp;
        final LocalDateTime ldt = LocalDateTime.now();
        final ZonedDateTime zdt = ldt.atZone(ZoneId.systemDefault());
        registrationDate = Date.from(zdt.toInstant());
    }

    public String getEmail() {
        return email;
    }

    public UserRole getRole() {
        return role;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public RegistrationTyp getRegistrationTyp() {
        return registrationTyp;
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
        final UserId other = (UserId) obj;
        if (email == null) {
            if (other.email != null) {
                return false;
            }
        } else if (!email.equals(other.email)) {
            return false;
        }
        return true;
    }

}
