package AfkoAPI.Model;

import javax.persistence.*;
import java.util.*;

@Entity
public class Account {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "firstLogin")
    private boolean firstLogin;

    @ManyToMany(fetch = FetchType.LAZY, targetEntity = Role.class)
    private Set<Role> roles = new HashSet<>();

    public Account() {}

    public Account(String firstName, String lastName, String email, String password, boolean firstLogin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.firstLogin = firstLogin;
        this.id = UUID.randomUUID().toString();
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {this.id = id;}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPassword() {
        return password;
    }


    public boolean isFirstLogin() {
        return firstLogin;
    }
    public void setPassword(String password){this.password = password;}


    public void setFirstLogin(boolean firstLogin) {
        this.firstLogin = firstLogin;
    }
}
