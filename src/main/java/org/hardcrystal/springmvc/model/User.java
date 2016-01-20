package org.hardcrystal.springmvc.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 20.01.2016.
 */
@Entity
@Table(name="APP_USER")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "SSO_ID", unique = true, nullable = false)
    @NotEmpty
    private String ssoId;

    @NotEmpty
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @NotEmpty
    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @NotEmpty
    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @NotEmpty
    @Column(name = "EMAIL", nullable = false)
    private String email;

    @NotEmpty
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "APP_USER_USER_PROFILE",
        joinColumns = {@JoinColumn(name = "USER_ID")},
        inverseJoinColumns = {@JoinColumn(name = "USER_PROFILE_ID")})
    private Set<UserProfile> userProfiles = new HashSet<UserProfile>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSsoId() {
        return ssoId;
    }

    public void setSsoId(String ssoId) {
        this.ssoId = ssoId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<UserProfile> getUserProfiles() {
        return userProfiles;
    }

    public void setUserProfiles(Set<UserProfile> userProfiles) {
        this.userProfiles = userProfiles;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof User))
            return false;
        User other = (User) obj;
        if (id == null){
            if (other.id != null) return false;
        }else if (!id.equals(other.id)) return false;
        if (ssoId == null) {
            if (other.ssoId != null) return false;
        }else if (!ssoId.equals(other.ssoId)) return false;

        return true;

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((ssoId == null) ? 0 : ssoId.hashCode());
        return result;
    }
}
