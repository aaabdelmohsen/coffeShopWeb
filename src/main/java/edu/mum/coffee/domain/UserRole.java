package edu.mum.coffee.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "UserRoles ")
public class UserRole implements Serializable {
    @Id
    @GeneratedValue
    public int id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username", referencedColumnName = "username")
    public User user;
    public String role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
