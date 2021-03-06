package AfkoAPI.jwt;

public class UserResponse {
    String email;
    String firstname;
    String lastname;
//    Organisation from user
    String token;
    boolean firstLogin;

    public UserResponse() { }

    public UserResponse(String email, String firstname, String lastname, String token, boolean firstLogin) {
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.token = token;
        this.firstLogin = firstLogin;
    }

    public boolean isFirstLogin() {
        return firstLogin;
    }

    public void setFirstLogin(boolean firstLogin) {
        this.firstLogin = firstLogin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
