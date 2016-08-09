package de.goldmann.apps.root.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Transient;
import javax.persistence.Version;

@MappedSuperclass
public class AbstractEntity
{

    @Id
    @GeneratedValue
    private Long   id;

    @Version
    private Long   version;

    @Transient
    private UUID   uuid;

    @Column(name = "UUID")
    private String uuidStr;

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractEntity that = (AbstractEntity) o;

        if (getUuid() != null ? !getUuid().equals(that.getUuid()) : that.getUuid() != null) return false;

        return true;
    }

    public Long getId()
    {
        return id;
    }

    public UUID getUuid()
    {
        if (uuidStr == null)
        {
            if (uuid == null)
            {
                uuid = UUID.randomUUID();
            }
            uuidStr = uuid.toString();
        }
        if (uuid == null && uuidStr != null)
        {
            uuid = UUID.fromString(uuidStr);
        }
        return uuid;
    }

    public String getUuidStr()
    {
        return uuidStr;
    }

    public Long getVersion()
    {
        return version;
    }

    @Override
    public int hashCode()
    {
        return getUuid() != null ? getUuid().hashCode() : 0;
    }

    /*
     * 
     * This method is mean for testing purposes only (create mock data), as we should not set Ids manually,
     * Hibernate will do it automatically
     */
    public void setId(Long id)
    {
        this.id = id;
    }

    @PrePersist
    protected void prePersist()
    {
        syncUuidString();
    }

    protected void syncUuidString()
    {
        if (null == uuidStr)
        {
            // initial method call fills the uuid
            getUuid();
        }
    }
}