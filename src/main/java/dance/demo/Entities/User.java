package dance.demo.Entities;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
public class User {
    @Id
    @NotEmpty
    @Column(name = "username", length = 20, unique = true)
    private String username;

    @NotEmpty
    @Column(name = "password", length = 100)
    private String password;

    @NotEmpty
    @Column(name = "firstname", length = 20)
    private String firstName;

    @NotEmpty
    @Column(name = "lastname", length = 20)
    private String lastName;

    @Column(name = "admin")
    private Boolean admin;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "usersgroups", joinColumns = {
            @JoinColumn(name = "username", referencedColumnName = "username")}, inverseJoinColumns = {
            @JoinColumn(name = "groupId", referencedColumnName = "group_id")})
    private List<Group> groups;

    public User() {
    }

    public User(String username, String password, String firstName, String lastName, Boolean admin) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.admin = admin;
    }

    public User(String username, String password, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.admin = false;
    }

    public User(String username, String password, String firstName, String lastName, Boolean admin, List<Group> groups) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.admin = admin;
        this.groups = groups;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
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
