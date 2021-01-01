package com.practice.spring.security.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class User extends BaseModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "is_active")
    private String isActive;
    @Column(name = "email")
    private String email;
    @OneToMany(targetEntity = Author.class, cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, mappedBy = "users")
    private Set<Author> authorSet;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User(String password, String email) {
        this.password = password;
        this.email = email;
    }

    public User(String username, String password, String isActive, String email) {
        this.username = username;
        this.password = password;
        this.isActive = isActive;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Author> getAuthorSet() {
        return authorSet;
    }

    public void setAuthorSet(Set<Author> authorSet) {
        this.authorSet = authorSet;
    }
}
