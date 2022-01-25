package AfkoAPI.RequestObjects;

import AfkoAPI.Model.Account;
import AfkoAPI.Model.Role;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AccountReturnObject {
    String id;
    String firstName;
    String lastName;
    String email;
    Set<Role> roles = new HashSet<>();
    boolean firstLogin;

    public AccountReturnObject() {
    }

    public AccountReturnObject(Account account) {
        this.id = account.getId();
        this.firstName = account.getFirstName();
        this.lastName = account.getLastName();
        this.email = account.getEmail();
        this.roles = account.getRoles();
        this.firstLogin = account.isFirstLogin();
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
