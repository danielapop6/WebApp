package dance.demo.Entities;

import dance.demo.Utils.Utils;

import javax.persistence.*;

@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
public class User {
    @Id
    @Column(name = "username", length = 20, nullable = false, unique = true)
    private String username;

    @Column(name = "password", length = 50, nullable = false)
    private String password;

    @Column(name = "firstname", length = 20, nullable = false)
    private String firstName;

    @Column(name = "lastname", length = 20, nullable = false)
    private String lastName;

    @Column(name = "admin")
    private Boolean admin;

    public User() {
    }

    public User(String username, String password, String firstName, String lastName, Boolean admin) {
        this.username = username;
        this.password=password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.admin = admin;
    }
    public User(String username, String password, String firstName, String lastName) {
        this.username = username;
        this.password=password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.admin = false;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
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

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", admin=" + admin +
                '}';
    }
}
